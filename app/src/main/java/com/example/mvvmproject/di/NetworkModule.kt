package com.example.mvvmproject.di

import com.example.mvvmproject.data.remote.api.ApiHelper
import com.example.mvvmproject.data.remote.api.ApiHelperImpl
import com.example.mvvmproject.data.remote.api.ApiService
import com.example.mvvmproject.utils.BASE_URL
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {

    factory {
        provideForecastApi(get())
    }

    single {
        provideRetrofit()
    }

    single<ApiHelper> {
        return@single ApiHelperImpl(get())
    }

}


fun provideRetrofit(): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_URL).client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create()).build()
}


fun provideForecastApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)