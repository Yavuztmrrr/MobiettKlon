package com.h5190041.mobiett.data.repository

import com.h5190041.mobiett.data.datasource.CategoryDataSource
import com.h5190041.mobiett.data.datasource.LoginDataSource
import com.h5190041.mobiett.data.datasource.remote.RemoteCategoryDataSource
import com.h5190041.mobiett.data.datasource.remote.RemoteLoginDataSource

import com.h5190041.mobiett.data.model.CategoryResponseItem
import com.h5190041.mobiett.data.model.LoginResponseItem

import com.h5190041.mobiett.util.Resource
import kotlinx.coroutines.flow.Flow

class LoginRepository {
    private var loginDataSource: LoginDataSource?=null

    init {
        loginDataSource= RemoteLoginDataSource()
    }

    fun kullanicilarGetir(): Flow<Resource<List<LoginResponseItem>>>
    {
        return loginDataSource!!.kullanicilarGetir()
    }
}