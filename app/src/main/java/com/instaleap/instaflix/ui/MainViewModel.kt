package com.instaleap.instaflix.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.instaleap.instaflix.domain.model.ErrorState
import com.instaleap.instaflix.domain.model.ResultState
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

    val isLoadingMore = mutableStateOf(false)

    fun getMovies(navigationItem: NavigationItem, page: Int = 1) {
        viewModelScope.launch {
            when (val result = repository.getMedia(navigationItem, page)) {
                is ResultState.Failure -> handleFailure(result.error)
                is ResultState.Success -> handleSuccess(result.data.map { it.toMediaUI() })
            }
        }
    }

    private suspend fun handleFailure(error: ErrorState) {
        _uiState.emit(MovieUiState.Error(error.toString()))
    }

    private suspend fun handleSuccess(newMovies: List<MediaUI>) {
        if (_uiState.value == MovieUiState.Loading) { // First time loading data state is Loading
            _uiState.emit(MovieUiState.Success(newMovies))
        } else {
            isLoadingMore.value = false
            val currentMovies = (_uiState.value as MovieUiState.Success).movies
            _uiState.emit(MovieUiState.Success(currentMovies + newMovies))
        }
    }
}

sealed class MovieUiState {
    data object Loading : MovieUiState()
    data class Success(val movies: List<MediaUI>) : MovieUiState()
    data class Error(val message: String?) : MovieUiState()
}
