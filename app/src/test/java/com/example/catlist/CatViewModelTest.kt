package com.example.catlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.catlist.TestHelper.getOrAwaitValue
import com.example.catlist.domain.datasoursce.CatRepository
import com.example.catlist.domain.datasoursce.ResponseState
import com.example.catlist.domain.model.CatDetail
import com.example.catlist.ui.viewModel.CatViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@ExperimentalCoroutinesApi
class CatViewModelTest {
    private val carRepo = mockk<CatRepository>(relaxed = true)
    private lateinit var viewModel: CatViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = CatViewModel(carRepo)
    }


    @Test
    fun successTest() {
        val mockobj = mockk<CatDetail>(relaxed = true)
        val mockList = mutableListOf<CatDetail>()
        mockList.add(mockobj)
        every {
            runBlocking {
                carRepo.getData(any())
            }
        } returns ResponseState.SuccessData(mockList)
        viewModel.getCatData()
        runBlocking {
            MainScope()
            Assert.assertEquals(
                (viewModel.responseData.getOrAwaitValue() as ResponseState.SuccessData).listOfImage,
                mockList
            )
        }


    }


    @Test
    fun failTest() {
        val mockobj = mockk<CatDetail>(relaxed = true)
        val mockList = mutableListOf<CatDetail>()
        mockList.add(mockobj)
        every {
            runBlocking {
                carRepo.getData(any())
            }
        } returns ResponseState.FailData
        viewModel.getCatData()
        runBlocking {
            MainScope()
            Assert.assertEquals(viewModel.responseData.getOrAwaitValue(), ResponseState.FailData)
        }


    }
}


object TestHelper {
    fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS,
        afterObserve: () -> Unit = {}
    ): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(o: T?) {
                data = o
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }
        this.observeForever(observer)
        afterObserve.invoke()
        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            this.removeObserver(observer)
            throw TimeoutException("LiveData value was never set.")
        }
        @Suppress("UNCHECKED_CAST")
        return data as T
    }
}