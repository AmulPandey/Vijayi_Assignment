package com.example.vijay_assignment.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vijay_assignment.Model.MovieRelease
import com.example.vijay_assignment.Repository.WatchModeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: WatchModeRepository
) : ViewModel() {

    private val _movieDetails = MutableStateFlow<MovieRelease?>(null)
    val movieDetails: StateFlow<MovieRelease?> = _movieDetails.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun fetchDetails(id: Int) {
        _isLoading.value = true
        repository.getDetails(id.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ details ->
                _movieDetails.value = details
                _isLoading.value = false
            }, { error ->
                _isLoading.value = false
                Log.e("DetailViewModel", "Error fetching details: ${error.message}")
            })
    }
}

