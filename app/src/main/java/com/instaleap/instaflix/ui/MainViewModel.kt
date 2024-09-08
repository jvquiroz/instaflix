package com.instaleap.instaflix.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.instaleap.instaflix.domain.model.ResultState
import com.instaleap.instaflix.domain.model.VideoContent
import com.instaleap.instaflix.domain.repository.VideoContentRepository
import com.instaleap.instaflix.ui.components.MediaUI
import com.instaleap.instaflix.ui.components.toMediaUI
import com.instaleap.instaflix.ui.navigation.NavigationItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: VideoContentRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<MovieUiState>(MovieUiState.Loading)
    val uiState: StateFlow<MovieUiState> = _uiState.asStateFlow()

    private val _displayErrorDialog = MutableStateFlow(false)
    val displayErrorDialog: StateFlow<Boolean> = _displayErrorDialog

    var isDataFetched = false

    val isLoadingMore = mutableStateOf(false)

    fun getMovies(navigationItem: NavigationItem, page: Int = 1) {
        isDataFetched = true
        viewModelScope.launch {
            when (val result = repository.getVideoContent(navigationItem, page)) {
                is ResultState.Failure -> handleFailure()
                is ResultState.Success -> handleSuccess(result)
            }
        }
    }

    private suspend fun handleFailure() {
        _displayErrorDialog.emit(true)
        isLoadingMore.value = false
    }

    private suspend fun handleSuccess(result: ResultState.Success<List<VideoContent>>) {

        val newMovies = result.data.map { it.toMediaUI() }
            if (_uiState.value == MovieUiState.Loading) { // First time loading data state is Loading
            _uiState.emit(MovieUiState.Success(newMovies, result.isCached))
        } else {
            isLoadingMore.value = false
            val currentMovies = (_uiState.value as MovieUiState.Success).movies
            _uiState.emit(MovieUiState.Success(currentMovies + newMovies))
        }
    }

    fun dismissErrorDialog() {
        _displayErrorDialog.value = false
    }
}

sealed class MovieUiState {
    data object Loading : MovieUiState()
    data class Success(val movies: List<MediaUI>, val isCached: Boolean = false) : MovieUiState()
}
