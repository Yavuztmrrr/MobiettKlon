package com.h5190041.mobiett.data.datasource.remote

import com.h5190041.mobiett.data.datasource.CategoryDataSource

import com.h5190041.mobiett.data.model.CategoryResponseItem

import com.h5190041.mobiett.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteCategoryDataSource : CategoryDataSource {

    override  fun otobusleriGetir(): Flow<Resource<List<CategoryResponseItem>>> = flow {
        try {
            emit(Resource.Loading())
            val response = ApiService.build().otobusleriGetir()
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