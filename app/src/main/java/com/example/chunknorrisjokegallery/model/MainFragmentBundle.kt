package com.example.chunknorrisjokegallery.model

data class MainFragmentBundle(
    val jokeList: MutableList<Joke> = mutableListOf(),
    var expanded: Boolean = false,
    var isConfigChange: Boolean = false
)
