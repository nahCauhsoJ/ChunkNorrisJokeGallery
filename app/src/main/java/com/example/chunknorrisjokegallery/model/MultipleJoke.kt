package com.example.chunknorrisjokegallery.model


import com.google.gson.annotations.SerializedName

data class MultipleJoke(
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    val jokes: List<Joke>
)