package com.pepivsky.nasaalbum

import com.pepivsky.nasaalbum.model.PhotoResponse
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val repository: GetPhotosRepository) {
    suspend fun getPhotos(): List<PhotoResponse> {
        return repository.getPhotos()
    }
}