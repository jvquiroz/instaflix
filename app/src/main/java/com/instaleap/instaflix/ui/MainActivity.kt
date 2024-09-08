package com.instaleap.instaflix.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.instaleap.instaflix.ui.components.screens.MainScreenRoot
import com.instaleap.instaflix.ui.theme.InstaflixTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            InstaflixTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    MainScreenRoot(
                        modifier = Modifier.padding(padding),
                    )
                }
            }
        }
    }
}