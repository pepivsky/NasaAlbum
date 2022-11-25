package com.pepivsky.nasaalbum

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pepivsky.nasaalbum.model.PhotoResponse

class NasaScreenViewModel: ViewModel() {

    init {

    }

    //live data privado, lista de objetos
    private val _imagesResponse = MutableLiveData<List<PhotoResponse>>()

    // get images from api
    fun getImages() {

    }
}