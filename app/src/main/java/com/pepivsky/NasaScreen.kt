package com.pepivsky

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pepivsky.nasaalbum.HomeUiState
import com.pepivsky.nasaalbum.NasaScreenViewModel
import com.pepivsky.nasaalbum.R
import com.pepivsky.nasaalbum.model.PhotoResponse

@Composable
fun NasaScreen(viewModel: NasaScreenViewModel) {
    val uiState = viewModel.homeUiState
    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is HomeUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is HomeUiState.Success -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    Header()
                    PhotosGrid(viewModel)
                }
            }
            is HomeUiState.Error -> {
                Text(modifier = Modifier.align(Alignment.Center), text = "Error")
            }
        }
    }
}

@Composable
fun Header() {
    Text(modifier = Modifier.padding(16.dp),text = "Nasa Mars Images", fontWeight = FontWeight.Bold, fontSize = 32.sp)
}

//@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotosGrid(viewModel: NasaScreenViewModel) {
    val photos: List<PhotoResponse> = viewModel.imagesResponse
    LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
        items(photos) { photo ->

            ItemPhoto(photoResponse = photo)
        }
    })


}

@Composable
fun ItemPhoto(photoResponse: PhotoResponse) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(6.dp)
        .aspectRatio(1F),
        elevation = 8.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(photoResponse.imageUrl)
                .crossfade(true)
                .build(), contentDescription = "image",
            contentScale = ContentScale.FillBounds,
            placeholder = painterResource(id = R.drawable.placeholder)
        )
    }
}
