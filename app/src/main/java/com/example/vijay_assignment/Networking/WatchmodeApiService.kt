package com.example.vijay_assignment.Networking

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface WatchModeApiService {
    @GET("releases")
    fun getReleases(@Query("apiKey") apiKey: String): Single<ApiResponse>
}

