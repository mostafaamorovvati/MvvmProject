package com.example.mvvmproject.di

import com.example.mvvmproject.data.remote.api.ApiHelper
import com.example.mvvmproject.data.remote.api.ApiHelperImpl
import com.example.mvvmproject.data.remote.api.ApiService
import com.example.mvvmproject.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {

    factory { provideOkHttpClient(get()) }
    factory { provideForecastApi(get()) }
    factory { provideLoggingInterceptor() }
    single { provideRetrofit(get()) }

    single<ApiHelper> {
        return@single ApiHelperImpl(get())
    }

}


fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}


fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    return OkHttpClient().newBuilder().connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES).writeTimeout(1, TimeUnit.MINUTES)
        .addInterceptor(loggingInterceptor).build()
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val logger = HttpLoggingInterceptor()
    logger.level = HttpLoggingInterceptor.Level.BODY
    return logger
}

fun provideForecastApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)