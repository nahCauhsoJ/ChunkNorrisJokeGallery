package com.example.chunknorrisjokegallery.model


import com.google.gson.annotations.SerializedName

data class SingleJoke(
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    val joke: Joke
)