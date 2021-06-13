package com.h5190041.mobiett.data.datasource


import com.h5190041.mobiett.data.model.CategoryResponseItem

import com.h5190041.mobiett.util.Resource
import kotlinx.coroutines.flow.Flow

interface CategoryDataSource {
    fun otobusleriGetir(): Flow<Resource<List<CategoryResponseItem>>>
}
