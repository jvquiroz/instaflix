package com.instaleap.instaflix.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import com.instaleap.instaflix.data.remote.MediaResponseDto
import com.instaleap.instaflix.data.remote.MediaService
import com.instaleap.instaflix.ui.navigation.AppNavigation
import com.instaleap.instaflix.ui.theme.InstaflixTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    internal lateinit var service: MediaService
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            /*val posts = produceState<MediaResponseDto>(
                initialValue = MediaResponseDto(),
                producer = {
                    value = service.getPopularMovies()
                    if (value.page > 0) {
                        println("Success")
                    }
                }
            )*/
            InstaflixTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    AppNavigation(modifier = Modifier.padding(padding))
                }
            }
        }
    }
}