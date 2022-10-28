package com.example.catlist.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catlist.domain.datasoursce.CatRepository
import com.example.catlist.domain.datasoursce.ResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CatViewModel @Inject constructor(
    val carRepo: CatRepository
) : ViewModel() {


    private val _responseData: MutableLiveData<ResponseState> = MutableLiveData()
    val responseData: LiveData<ResponseState> = _responseData

    fun getCatData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _responseData.postValue(carRepo.getData())
            }
        }
    }

}