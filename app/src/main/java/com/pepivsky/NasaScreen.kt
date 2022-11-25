package com.pepivsky

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pepivsky.nasaalbum.NasaScreenViewModel
import com.pepivsky.nasaalbum.R
import com.pepivsky.nasaalbum.model.PhotoResponse

@Composable
fun NasaScreen(viewModel: NasaScreenViewModel) {
    //Text(text = "Hello $name!")
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {

            Header()
            PhotosGrid(viewModel)
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
    val photos: List<PhotoResponse> by viewModel.imagesResponse.observeAsState(initial = emptyList<PhotoResponse>())
    //viewModel.getImages()
    //photos = viewModel.getImages()
    //val list = listOf<PhotoResponse>()

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
        .padding(10.dp)) {
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
