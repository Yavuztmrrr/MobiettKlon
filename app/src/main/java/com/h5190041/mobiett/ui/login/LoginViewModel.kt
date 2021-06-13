package com.h5190041.mobiett.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.h5190041.mobiett.data.model.CategoryResponseItem
import com.h5190041.mobiett.data.model.LoginResponseItem

import com.h5190041.mobiett.data.repository.CategoryRepository
import com.h5190041.mobiett.data.repository.LoginRepository
import com.h5190041.mobiett.util.ResourceStatus
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private  val loginRepository: LoginRepository =LoginRepository()

    init {
        kullanicilariGetir()
    }


    var loginLiveData = MutableLiveData<List<LoginResponseItem>>()
    var error =    MutableLiveData<Throwable>()
    var loading   : MutableLiveData<Boolean>? = MutableLiveData()

    fun kullanicilariGetir()  = viewModelScope.launch {

        loginRepository.kullanicilarGetir()


            .asLiveData(viewModelScope.coroutineContext).observeForever {

                when(it.status) {
                    ResourceStatus.LOADING -> {
                        loading?.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        loginLiveData.postValue(it.data!!)
                        loading?.postValue(false)
                    }

                    ResourceStatus.ERROR -> {
                        Log.e("ERROR", "${it.throwable}")
                        error?.postValue(it.throwable!!)
                        loading?.postValue(false)
                    }
                }
            }
    }
}