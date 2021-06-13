package com.h5190041.mobiett.data.repository

import com.h5190041.mobiett.data.datasource.CategoryDataSource
import com.h5190041.mobiett.data.datasource.remote.RemoteCategoryDataSource

import com.h5190041.mobiett.data.model.CategoryResponseItem

import com.h5190041.mobiett.util.Resource
import kotlinx.coroutines.flow.Flow

class CategoryRepository {

    private var categoryDataSource: CategoryDataSource?=null

    //İlk çalişicak methodtur. categoryDataSource u RemoteCategoryDataSource eşitler.
    init {
        categoryDataSource= RemoteCategoryDataSource()
    }

    //categoryDataSource da bulunan otobusleriGetir func return eder.
    fun otobusleriGetir(): Flow<Resource<List<CategoryResponseItem>>>
    {
        return categoryDataSource!!.otobusleriGetir()
    }
}