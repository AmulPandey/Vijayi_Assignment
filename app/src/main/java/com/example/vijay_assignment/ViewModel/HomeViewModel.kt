package com.example.vijay_assignment.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vijay_assignment.Model.MovieRelease
import com.example.vijay_assignment.Repository.WatchModeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: WatchModeRepository
) : ViewModel() {

    private val _movies = MutableStateFlow<List<MovieRelease>>(emptyList())
    val movies: StateFlow<List<MovieRelease>> = _movies.asStateFlow()

    private val _tvShows = MutableStateFlow<List<MovieRelease>>(emptyList())
    val tvShows: StateFlow<List<MovieRelease>> = _tvShows.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        fetchMoviesAndTVShows()
    }

    private fun fetchMoviesAndTVShows() {
        _isLoading.value = true
        Single.zip(
            repository.getMovies(),
            repository.getTVShows(),
            { movies, tvShows -> Pair(movies, tvShows) }
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ (movies, tvShows) ->
                _movies.value = movies
                _tvShows.value = tvShows
                _isLoading.value = false
            }, { error ->
                _isLoading.value = false
                Log.e("HomeViewModel", "Error fetching data: ${error.message}")
            })
    }
}

