package com.instaleap.instaflix.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.instaleap.instaflix.R

@Composable
fun VideoContentGrid(
    modifier: Modifier = Modifier,
    itemsList: List<MediaUI>,
    onLoadMore: (Int) -> Unit,
    currentPage: MutableIntState,
    isLoading: MutableState<Boolean>,
    isCached: Boolean,
    onItemClick: (MediaUI) -> Unit
) {
    val shape = RoundedCornerShape(4.dp)
    val itemContentModifier = Modifier.padding(8.dp)
    val itemBorderModifier = Modifier
        .border(
            border = BorderStroke(1.dp, Color.LightGray),
            shape = shape
        )
    val itemModifier = itemContentModifier.then(itemBorderModifier)
        .height(200.dp)

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
    ) {
        if (isCached) {
            item(span = { GridItemSpan(3) }, key = "cached_warning") {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = itemBorderModifier
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.error_displaying_cached_data),
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(imageVector = Icons.Default.Info, contentDescription = "Info")
                }
            }
        }

        items(items = itemsList, key = { it.id }) { videoContent ->
            val iconPainter = rememberVectorPainter(image = Icons.Default.Info)
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/w342/${videoContent.poster}")
                    .crossfade(true)
                    .build(),
                placeholder = iconPainter,
                contentDescription = videoContent.title,
                contentScale = ContentScale.Crop,
                modifier = itemModifier.clip(shape)
                    .clickable {
                        onItemClick(videoContent)
                    },
            )
        }

        item(span = { GridItemSpan(3) }, key = "load_more_button") {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                if (isLoading.value) {
                    CircularProgressIndicator()
                } else if (currentPage.intValue < 11) {
                    Button(onClick = {
                        isLoading.value = true
                        currentPage.intValue++
                        onLoadMore(currentPage.intValue)
                    }) {
                        Text(text = stringResource(R.string.load_more))
                    }
                }
            }
        }
    }
}