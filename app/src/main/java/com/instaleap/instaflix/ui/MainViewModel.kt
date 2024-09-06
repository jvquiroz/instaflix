package com.instaleap.instaflix.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.instaleap.instaflix.domain.model.ResultState
import com.instaleap.instaflix.domain.repository.MediaRepository
import com.instaleap.instaflix.ui.components.MediaUI
import com.instaleap.instaflix.ui.components.toMediaUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MediaRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<MovieUiState>(MovieUiState.Loading)
    val uiState: StateFlow<MovieUiState> = _uiState.asStateFlow()
    val isLoadingMore = mutableStateOf(false)

    //fun getMovies(route: String, page: Int = 1, isLoading: MutableState<Boolean> = mutableStateOf(false)) {
    fun getMovies(route: String, page: Int = 1) {
        viewModelScope.launch {
            when (val result = repository.getMedia(route, page)) {
                is ResultState.Failure -> {
                    _uiState.emit(MovieUiState.Error(result.error.toString()))
                }
                is ResultState.Success -> {
                    if (_uiState.value is MovieUiState.Loading) {
                        _uiState.emit(MovieUiState.Success(result.data.map { it.toMediaUI() }))
                    } else if (_uiState.value is MovieUiState.Success) {
                        isLoadingMore.value = false
                        _uiState.emit(
                            MovieUiState.Success(
                                movies =(_uiState.value as MovieUiState.Success).movies + result.data.map { it.toMediaUI() }
                            )
                        )
                    }


                }
            }
        }
    }
}
sealed class MovieUiState {
    data object Loading : MovieUiState()
    data class Success(var movies: List<MediaUI>) : MovieUiState()
    data class Error(val message: String?) : MovieUiState()
}