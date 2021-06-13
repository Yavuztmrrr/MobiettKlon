package com.h5190041.mobiett.data.datasource



import com.h5190041.mobiett.data.model.LoginResponseItem

import com.h5190041.mobiett.util.Resource
import kotlinx.coroutines.flow.Flow

interface LoginDataSource {
    fun kullanicilarGetir(): Flow<Resource<List<LoginResponseItem>>>
}
