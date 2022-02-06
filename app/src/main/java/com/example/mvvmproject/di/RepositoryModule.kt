package com.example.mvvmproject.di

import com.example.mvvmproject.data.repository.PhotoRepository
import com.example.mvvmproject.data.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory {
        PhotoRepository(get())
    }

    factory {
        UserRepository(get())
    }

}