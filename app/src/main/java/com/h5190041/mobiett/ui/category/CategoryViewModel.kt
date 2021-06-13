package com.h5190041.mobiett.ui.category

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.h5190041.mobiett.data.model.CategoryResponseItem


import com.h5190041.mobiett.data.repository.CategoryRepository

import com.h5190041.mobiett.util.ResourceStatus
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    private  val categoryRepository: CategoryRepository =CategoryRepository()

    //İlk çalişicak methodtur
    init {
        otobusleriGetir()
    }

    var categoryLiveData = MutableLiveData<List<CategoryResponseItem>>()
    var error =    MutableLiveData<Throwable>()
    var loading   : MutableLiveData<Boolean>? = MutableLiveData()

    //İnternetten çekilen verileri sayfa geldiğinde  getirmeye yarar
    fun otobusleriGetir()  = viewModelScope.launch {

        categoryRepository.otobusleriGetir()


            .asLiveData(viewModelScope.coroutineContext).observeForever {

                when(it.status) {
                    ResourceStatus.LOADING -> {
                        loading?.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        categoryLiveData.postValue(it.data!!)
                        loading?.postValue(false)
                    }

                    ResourceStatus.ERROR -> {
                        Log.e(Constants.ERROR, "${it.throwable}")
                        error?.postValue(it.throwable!!)
                        loading?.postValue(false)
                    }
                }
            }
    }
}
