package com.pepivsky.nasaalbum

import com.pepivsky.nasaalbum.model.PhotoResponse
import retrofit2.Response
import retrofit2.http.GET

interface GetPhotosClient {
    // endpount para obtener las imagenes en un arreglo JSON
    @GET("photos")
    suspend fun getPhotos(): List<PhotoResponse>
}