package com.pepivsky.nasaalbum

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pepivsky.nasaalbum.model.PhotoResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

@HiltViewModel
class NasaScreenViewModel @Inject constructor(private val getPhotosUseCase: GetPhotosUseCase): ViewModel() {

    init {
        getImages()
    }

    //live data privado, lista de objetos
    private val _imagesResponse = MutableLiveData<List<PhotoResponse>>()
    val imagesResponse = _imagesResponse

    // liveData para saber cuando esta cargando
    /*private val _uiState = MutableLiveData<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState*/
    var homeUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)


    // get images from api
    private fun getImages() {
       //homeUiState = HomeUiState.Loading

        viewModelScope.launch {
            Log.d("pp", "init call")

            homeUiState = try {
                val result = getPhotosUseCase.getPhotos()
                Log.d("pp", "get JSON $result")
                _imagesResponse.value = result
                HomeUiState.Success
            } catch (e: IOException) {
                Log.d("pp", "error")
                HomeUiState.Error
            }
        }
    }
}


sealed interface HomeUiState {
    object Success: HomeUiState
    object Loading : HomeUiState
    object Error : HomeUiState
}
