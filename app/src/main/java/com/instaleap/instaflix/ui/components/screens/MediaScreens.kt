package com.instaleap.instaflix.ui.components.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.instaleap.instaflix.R
import com.instaleap.instaflix.ui.MainViewModel
import com.instaleap.instaflix.ui.MovieUiState
import com.instaleap.instaflix.ui.components.MediaUI
import com.instaleap.instaflix.ui.components.VideoContentGrid
import com.instaleap.instaflix.ui.navigation.NavigationItem
import com.instaleap.instaflix.ui.theme.NoNetwork

@Composable
fun MediaScreen(
    modifier: Modifier = Modifier,
    navigationItem: NavigationItem,
    onItemClick: (MediaUI) -> Unit
) {
    val currentPage = rememberSaveable { mutableIntStateOf(1) }
    val viewModel: MainViewModel = hiltViewModel()
    val isErrorDialogOpen by viewModel.displayErrorDialog.collectAsStateWithLifecycle()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        LaunchedEffect(Unit) {
            if (!viewModel.isDataFetched) {
                viewModel.getMovies(navigationItem)
            }
        }

        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        when (val state = uiState) {
            is MovieUiState.Loading -> LoadingScreen()
            is MovieUiState.Success -> {
                VideoContentGrid(
                    isCached = state.isCached,
                    currentPage = currentPage,
                    isLoading = viewModel.isLoadingMore,
                    itemsList = state.movies,
                    onItemClick = onItemClick,
                    onLoadMore = { page ->
                        viewModel.isLoadingMore.value = true
                        viewModel.getMovies(navigationItem, page)
                    }
                )
            }
        }
        if (isErrorDialogOpen) {
            AlertDialog(
                onDismissRequest = { viewModel.dismissErrorDialog() },
                icon = {
                    Icon(Icons.Filled.NoNetwork, contentDescription = "Info")
                },
                title = {
                    Text(text = stringResource(R.string.connection_error))
                },
                text = {
                    Text(stringResource(R.string.check_internet_connection))
                },
                confirmButton = {
                    Button(onClick = { viewModel.dismissErrorDialog() }) {
                        Text(stringResource(R.string.ok))
                    }
                }
            )

        }
    }
}
