package com.pepivsky.nasaalbum

import com.google.gson.GsonBuilder
//import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
//import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // scope para toda la app
class NetworkModule {

    // anotaciones para proveer un singleton, es decir que hay una unica instancia
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://android-kotlin-fun-mars-server.appspot.com")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }
}