package com.instaleap.instaflix.ui.components.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.instaleap.instaflix.ui.navigation.ScreenRoute

@Composable
fun DetailsScreen(videoContent: ScreenRoute.DetailsRoute) {
    val borderShape = RoundedCornerShape(4.dp)
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val iconPainter = rememberVectorPainter(image = Icons.Default.Info)

        // Background Image
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://image.tmdb.org/t/p/w780/${videoContent.backdrop}")
                .crossfade(true)
                .build(),
            placeholder = iconPainter,
            contentDescription = "",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        // Gradient Overlay
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(232.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, MaterialTheme.colorScheme.background),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )

        val scrollState = rememberScrollState()

        // Content Column
        Column(
            modifier = Modifier
                .padding(top = 0.dp, start = 20.dp, end = 20.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(200.dp))

            // Poster and Details Row
            Row {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://image.tmdb.org/t/p/w342/${videoContent.poster}")
                        .crossfade(true)
                        .build(),
                    placeholder = iconPainter,
                    contentDescription = videoContent.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .border(
                            border = BorderStroke(1.dp, Color.LightGray),
                            shape = borderShape
                        )
                        .clip(borderShape)
                )

                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text(
                        text = videoContent.title,
                        style = MaterialTheme.typography.displayMedium
                    )
                    Text(
                        text = videoContent.releaseDate,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Overview Text
            Text(
                text = videoContent.overview,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
