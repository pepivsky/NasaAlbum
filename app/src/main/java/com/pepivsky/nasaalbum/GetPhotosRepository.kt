package com.pepivsky.nasaalbum

import com.pepivsky.nasaalbum.model.PhotoResponse
import javax.inject.Inject

class GetPhotosRepository @Inject constructor(private val api: GetPhotosService){

    suspend fun getPhotos(): List<PhotoResponse> {
        return api.getPhotos()
    }
}