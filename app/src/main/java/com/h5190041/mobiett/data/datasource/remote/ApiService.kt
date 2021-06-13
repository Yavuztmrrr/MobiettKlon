package com.h5190041.mobiett.data.datasource.remote


import com.h5190041.mobiett.data.model.CategoryResponseItem
import com.h5190041.mobiett.data.model.LoginResponseItem
import com.h5190041.mobiett.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    //github da bulunan json dosyasını get yapılan yer
    @GET("mobiettjson.json")
    suspend fun otobusleriGetir(): Response<List<CategoryResponseItem>>

    @GET("userjson.json")
    suspend fun kullanicilarGetir(): Response<List<LoginResponseItem>>



    companion object  {

        fun build(): ApiService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHtppClient =  OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .client(okHtppClient)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}