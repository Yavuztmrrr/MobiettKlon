package com.h5190041.mobiett.data.datasource.remote


import com.h5190041.mobiett.data.datasource.LoginDataSource


import com.h5190041.mobiett.data.model.LoginResponseItem

import com.h5190041.mobiett.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteLoginDataSource : LoginDataSource {

    override  fun kullanicilarGetir(): Flow<Resource<List<LoginResponseItem>>> = flow {
        try {
            emit(Resource.Loading())
            val response = ApiService.build().kullanicilarGetir()
            if (response.isSuccessful) {

                response.body()?.let {
                    emit(Resource.Success(it))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e))
            e.printStackTrace()
        }
    }


}