package com.pepivsky.nasaalbum

import com.pepivsky.nasaalbum.model.PhotoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

class GetPhotosService @Inject constructor(private val retrofit: Retrofit) {

    suspend fun getPhotos(): List<PhotoResponse> {
        return withContext(Dispatchers.IO) {
             retrofit.create(GetPhotosClient::class.java).getPhotos()
        }
    }
}