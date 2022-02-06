package com.example.mvvmproject.di

import com.example.mvvmproject.data.repository.PhotoRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory {
        PhotoRepository(get())
    }

}