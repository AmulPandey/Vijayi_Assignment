package com.example.vijay_assignment.Repository

import com.example.vijay_assignment.Model.MovieRelease
import com.example.vijay_assignment.Networking.WatchModeApiService
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WatchModeRepository @Inject constructor(private val api: WatchModeApiService) {

    fun getMovies(): Single<List<MovieRelease>> =
        api.getReleases("FXMvgGQNLns1LBpCIcS8xpq6Jbk8oW7TFKmWtOGH")
            .map { response ->
                response.releases.filter { it.type == "movie" }
            }

    fun getTVShows(): Single<List<MovieRelease>> =
        api.getReleases("FXMvgGQNLns1LBpCIcS8xpq6Jbk8oW7TFKmWtOGH")
            .map { response ->
                response.releases.filter { it.type == "tv_series" }
            }

    fun getDetails(id: String): Single<MovieRelease> =
        api.getReleases("FXMvgGQNLns1LBpCIcS8xpq6Jbk8oW7TFKmWtOGH")
            .map { response ->
                response.releases.first { it.id == id.toInt() }
            }
}
