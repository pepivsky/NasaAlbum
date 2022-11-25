package com.pepivsky.nasaalbum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pepivsky.nasaalbum.model.PhotoResponse
import com.pepivsky.nasaalbum.ui.theme.NasaAlbumTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NasaAlbumTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NasaScreen()
                }
            }
        }
    }
}

@Composable
fun NasaScreen() {
    //Text(text = "Hello $name!")
    Box(modifier = Modifier.fillMaxSize()) {
        Header()
    }
}

@Composable
fun Header() {
    Text(modifier = Modifier.padding(16.dp),text = "Nasa Mars Images", fontWeight = FontWeight.Bold, fontSize = 32.sp)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotosGrid() {
    /*LazyVerticalGrid(cells = GridCells.Fixed(2), content = {

    })*/
}

@Composable
fun ItemPhoto(photoResponse: PhotoResponse) {
    Card(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(photoResponse.imageUrl)
                .crossfade(true)
                .build(), contentDescription = "image"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NasaAlbumTheme {
        NasaScreen()
    }
}