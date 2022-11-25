package com.pepivsky.nasaalbum

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pepivsky.nasaalbum.model.PhotoResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

@HiltViewModel
class NasaScreenViewModel @Inject constructor(private val getPhotosUseCase: GetPhotosUseCase): ViewModel() {

    init {
        getImages()
    }

    //live data privado, lista de objetos
    private val _imagesResponse = MutableLiveData<List<PhotoResponse>>()
    val imagesResponse = _imagesResponse


    // get images from api
    fun getImages() {
        viewModelScope.launch {
            Log.d("pp", "init call")

            try {
                val result = getPhotosUseCase.getPhotos()
                Log.d("pp", "get JSON $result")
                _imagesResponse.value = result
            } catch (e: IOException) {
                Log.d("pp", "error")
            }
        }
    }
}