package com.pepivsky

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pepivsky.nasaalbum.HomeUiState
import com.pepivsky.nasaalbum.NasaScreenViewModel
import com.pepivsky.nasaalbum.R
import com.pepivsky.nasaalbum.model.PhotoResponse
import com.pepivsky.nasaalbum.model.Routes
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun NasaScreen(viewModel: NasaScreenViewModel, navigationController: NavHostController) {
    val uiState = viewModel.homeUiState
    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is HomeUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is HomeUiState.Success -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    Header()
                    PhotosGrid(viewModel, navigationController)
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
fun PhotosGrid(viewModel: NasaScreenViewModel, navigationController: NavHostController) {
    val photos: List<PhotoResponse> = viewModel.imagesResponse
    LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
        items(photos) { photo ->

            ItemPhoto(photoResponse = photo, navigationController)
        }
    })


}

@Composable
fun ItemPhoto(photoResponse: PhotoResponse, navigationController: NavHostController) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(6.dp)
        .aspectRatio(1F)
        .clickable {
            val encodedUrl = URLEncoder.encode(photoResponse.imageUrl, StandardCharsets.UTF_8.toString())
            Log.d("pp", "tap! $encodedUrl")
            navigationController.navigate(Routes.Pantalla4.createRoute(encodedUrl))
        },
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

/*
@Preview()
@Composable
fun ZoomableImage() {
    val scale = remember { mutableStateOf(1f) }
    val rotationState = remember { mutableStateOf(1f) }
    Box(
        modifier = Modifier
            .clip(RectangleShape) // Clip the box content
            .fillMaxSize() // Give the size you want...
            .background(Color.Gray)
            .pointerInput(Unit) {
                detectTransformGestures { centroid, pan, zoom, rotation ->
                    scale.value *= zoom
                    rotationState.value += rotation
                }
            }
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.Center) // keep the image centralized into the Box
                .graphicsLayer(
                    // adding some zoom limits (min 50%, max 200%)
                    scaleX = maxOf(.5f, minOf(3f, scale.value)),
                    scaleY = maxOf(.5f, minOf(3f, scale.value)),
                    rotationZ = rotationState.value
                ),
            contentDescription = null,
            painter = painterResource(R.drawable.placeholder)
        )
    }
}*/


