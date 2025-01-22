package com.example.vijay_assignment.Model

data class MovieRelease(
    val id: Int,
    val title: String,
    val type: String,
    val imdb_id: String?,
    val tmdb_id: Int?,
    val tmdb_type: String?,
    val season_number: Int?,
    val poster_url: String?,
    val source_release_date: String?,
    val source_id: Int?,
    val source_name: String?,
    val is_original: Int?
)
