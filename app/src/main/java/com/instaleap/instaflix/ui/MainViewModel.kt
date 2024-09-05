package com.instaleap.instaflix.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.instaleap.instaflix.domain.repository.MediaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MediaRepository
): ViewModel() {
    fun getPopularMovies() {

        viewModelScope.launch {
            when (val result = repository.getPopularMovies()) {

            }
        }

    }

}