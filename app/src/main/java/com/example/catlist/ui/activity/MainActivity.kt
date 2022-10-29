package com.example.catlist.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catlist.MyApplication
import com.example.catlist.databinding.ActivityMainBinding
import com.example.catlist.domain.datasoursce.ResponseState
import com.example.catlist.domain.model.CatDetail
import com.example.catlist.ui.adapter.CatDisplayAdapter
import com.example.catlist.ui.viewModel.CatViewModel
import com.example.catlist.utils.AdapterTypeFactoryImpl
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
        setRecyclerView()
        setObserver()
        mainViewModel.getCatData()

    }

    private fun setRecyclerView() {
        catListAdapter = CatDisplayAdapter(this,AdapterTypeFactoryImpl())
        binding.catList.layoutManager = LinearLayoutManager(this)
        binding.catList.adapter = catListAdapter

        binding.catList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1) && dy > 0) {
                    binding.loadMe.visibility = View.VISIBLE
                    mainViewModel.getCatData()
                }
            }
        })
    }

    private fun setObserver() {
        mainViewModel.responseData.observe(this) {
            when (it) {
                is ResponseState.FailData -> Toast.makeText(
                    this,
                    "Something Went Wrong",
                    Toast.LENGTH_SHORT
                ).show()
                is ResponseState.SuccessData -> successInflateData(it.listOfImage)
            }
        }
    }

    private fun successInflateData(listOfImage: List<CatDetail>) {
        binding.loadMe.visibility = View.GONE
        catListAdapter.setupdatedList(listOfImage)
    }
}