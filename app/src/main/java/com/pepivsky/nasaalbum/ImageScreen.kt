package com.pepivsky.nasaalbum

import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ZoomableBox(
    modifier: Modifier = Modifier,
    minScale: Float = 0.1f,
    maxScale: Float = 5f,
    content: @Composable ZoomableBoxScope.() -> Unit
) {
    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var size by remember { mutableStateOf(IntSize.Zero) }
    Box(
        modifier = modifier
            .clip(RectangleShape)
            .onSizeChanged { size = it }
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale = maxOf(minScale, minOf(scale * zoom, maxScale))
                    val maxX = (size.width * (scale - 1)) / 2
                    val minX = -maxX
                    offsetX = maxOf(minX, minOf(maxX, offsetX + pan.x))
                    val maxY = (size.height * (scale - 1)) / 2
                    val minY = -maxY
                    offsetY = maxOf(minY, minOf(maxY, offsetY + pan.y))
                }
            }
    ) {
        val scope = ZoomableBoxScopeImpl(scale, offsetX, offsetY)
        scope.content()
    }
}

//@Preview()
@Composable
fun ImageWithZoom(photoURL: String) {
    /*val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://example.com/image.jpg")
            .size(Size.ORIGINAL) // Set the target size to load the image at.
            .build()
    )*/


    ZoomableBox {
        AsyncImage(
            modifier = Modifier
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offsetX,
                    translationY = offsetY
                )
                .fillMaxSize(),
            model = ImageRequest.Builder(LocalContext.current)
                .data(photoURL)
                .crossfade(true)
                .build(), contentDescription = "image",
            placeholder = painterResource(id = R.drawable.placeholder)
        )
    }


}

/*
@Preview()
@Composable
fun ImageWithZoom() {

    ZoomableBox {
        Image(
            modifier = Modifier
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offsetX,
                    translationY = offsetY
                ).fillMaxSize(),
            painter = painterResource(R.drawable.placeholder),
            contentDescription = null
        )
    }
}

*/

interface ZoomableBoxScope {
    val scale: Float
    val offsetX: Float
    val offsetY: Float
}

private data class ZoomableBoxScopeImpl(
    override val scale: Float,
    override val offsetX: Float,
    override val offsetY: Float
) : ZoomableBoxScope