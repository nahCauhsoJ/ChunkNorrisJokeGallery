package com.example.chunknorrisjokegallery.network

import com.example.chunknorrisjokegallery.model.Joke
import java.lang.Exception

sealed class Results {
    object LOADING: Results()
    data class SUCCESS(val response: List<Joke>): Results()
    data class ERROR(val error: Exception): Results()
}
