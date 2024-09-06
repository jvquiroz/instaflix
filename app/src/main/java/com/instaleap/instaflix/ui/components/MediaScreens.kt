package com.instaleap.instaflix.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.instaleap.instaflix.R
import com.instaleap.instaflix.ui.MainViewModel
import com.instaleap.instaflix.ui.MovieUiState


@Composable
fun MediaScreen(modifier: Modifier = Modifier, route: String) {
    val currentPage = rememberSaveable { mutableIntStateOf(1) }
    val viewModel: MainViewModel = hiltViewModel()
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LaunchedEffect(Unit) {
            viewModel.getMovies(route)
        }
        val uiState by viewModel.uiState.collectAsState()
        when (val state = uiState) {
            is MovieUiState.Loading -> LoadingScreen()
            is MovieUiState.Success -> {
                MediaGrid(
                    currentPage = currentPage,
                    isLoading = viewModel.isLoadingMore,
                    itemsList = state.movies,
                    onLoadMore = { page ->
                        viewModel.isLoadingMore.value = true
                        viewModel.getMovies(route, page)
                    }
                )
            }
            is MovieUiState.Error -> {
                ErrorScreen(errorMessage = state.message)
            }
        }
    }
}

@Composable
fun MediaGrid(
    modifier: Modifier = Modifier,
    itemsList: List<MediaUI>,
    onLoadMore: (Int) -> Unit,
    currentPage: MutableIntState,
    isLoading: MutableState<Boolean>,
) {
    val itemModifier = Modifier
        .border(1.dp, Color.Blue)
        .height(200.dp)
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()){
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
        ) {
            items(itemsList) { photo ->
                Text("Single item: ${photo.title}", itemModifier)
            }
            item(span = { GridItemSpan(3) }) {
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)) {
                    if (isLoading.value) {
                        CircularProgressIndicator()
                    } else if (currentPage.intValue < 3) {
                        Button(
                            onClick = {
                                isLoading.value = true
                                currentPage.intValue++
                                onLoadMore(currentPage.intValue)
                            }
                        ) {
                            Text(text = stringResource(R.string.load_more))
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(errorMessage: String?) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = errorMessage ?: "Unknown error",
            color = Color.Red,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}