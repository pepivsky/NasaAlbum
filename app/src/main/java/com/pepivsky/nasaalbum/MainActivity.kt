package com.pepivsky.nasaalbum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import com.pepivsky.nasaalbum.ui.theme.NasaAlbumTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pepivsky.NasaScreen
import com.pepivsky.nasaalbum.model.Routes

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: NasaScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NasaAlbumTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //NasaScreen(viewModel)

                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.AlbumScreen.route
                    ) {
                        composable(Routes.AlbumScreen.route) { NasaScreen(viewModel = viewModel, navigationController) }

                       /* composable(Routes.ImageScreen.route) {
                            val argument = it.arguments?.getString(Routes.ImageScreen.KEY_PHOTO_URL).orEmpty()
                            ImageWithZoom(argument)
                        }*/

                        composable(Routes.Pantalla4.route,
                            arguments = listOf(navArgument(Routes.Pantalla4.KEY_NAME) { type = NavType.StringType })
                            ) {
                            val argument = it.arguments?.getString(Routes.Pantalla4.KEY_NAME)
                            ImageWithZoom(argument.orEmpty())
                        }

                        /*
                         composable(Routes.Pantalla4.route) {
                            val argument = it.arguments?.getString(Routes.Pantalla4.KEY_NAME)
                            ImageWithZoom(argument.orEmpty())
                        }

                         */



                        /*composable(
                            Routes.ImageScreen.route,
                            arguments = listOf(navArgument(Routes.ImageScreen.KEY_PHOTO) { type = NavType.P })

                        ) {
                            val argument = it.arguments?.getInt(Routes.Pantalla2.KEY_AGE) ?: 0
                            Screen2(navigationController, argument)
                        }

                        composable(Routes.Pantalla3.route) { Screen3(navigationController) }

                        // navegacion con argumento String
                        composable(Routes.Pantalla4.route) {
                            val argument = it.arguments?.getString(Routes.Pantalla4.KEY_NAME)
                            Screen4(navigationController, argument.orEmpty())
                        }

                        composable(Routes.Pantalla5.route,
                            arguments = listOf(navArgument(Routes.Pantalla5.KEY_COUNTRY, {defaultValue = "defaultCountry"}))
                        )
                        {
                            Screen5(navigationController = navigationController, it.arguments?.getString(Routes.Pantalla5.KEY_COUNTRY))
                        }*/

                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NasaAlbumTheme {
        //R.drawable.placeholder
        //NasaScreen(viewModel = )
    }
}