package com.example.chunknorrisjokegallery.network

import com.example.chunknorrisjokegallery.model.MultipleJoke
import com.example.chunknorrisjokegallery.model.SingleJoke
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET(RANDOM_ONE)
    suspend fun getRandomOne(
        @Query("exclude") exclude_category: List<String>? = null,
        @Query("firstName") first_name: String? = null,
        @Query("lastName") last_name: String? = null
    ): Response<SingleJoke>

    @GET(RANDOM_MULTI)
    suspend fun getRandomMultiple(
        @Path("count") joke_count: Int = 20,
        @Query("exclude") exclude_category: List<String>? = null,
        @Query("firstName") first_name: String? = null,
        @Query("lastName") last_name: String? = null
    ): Response<MultipleJoke>

    companion object {
        const val BASE_URL = "http://api.icndb.com/"
        private const val BASE_PATH = "jokes"
        private const val RANDOM_ONE = "${BASE_PATH}/random"
        private const val RANDOM_MULTI = "${BASE_PATH}/random/{count}"
    }
}