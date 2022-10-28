package com.example.catlist.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catlist.MyApplication
import com.example.catlist.databinding.ActivityMainBinding
import com.example.catlist.domain.datasoursce.ResponseState
import com.example.catlist.domain.model.CatDetail
import com.example.catlist.ui.adapter.CatDisplayAdapter
import com.example.catlist.ui.viewModel.CatViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModeFactory: ViewModelProvider.Factory
    private lateinit var mainViewModel: CatViewModel
    private lateinit var catListAdapter: CatDisplayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mainViewModel = ViewModelProvider(this, viewModeFactory)[CatViewModel::class.java]

        catListAdapter = CatDisplayAdapter(this)
        binding.catList.layoutManager = LinearLayoutManager(this)
        binding.catList.adapter = catListAdapter

        setObserver()
        mainViewModel.getCatData()

    }

    private fun setObserver() {
        mainViewModel.responseData.observe(this) {
            when (it) {
                ResponseState.FailData -> Log.w("Hii", "on Fail")
                is ResponseState.SuccessData -> successInflateData(it.listOfImage)
            }
        }
    }

    private fun successInflateData(listOfImage: List<CatDetail>) {
        catListAdapter.setupdatedList(listOfImage)
    }
}