package com.example.chunknorrisjokegallery.utils

import com.example.chunknorrisjokegallery.model.Joke
import com.example.chunknorrisjokegallery.model.MultipleJoke
import com.example.chunknorrisjokegallery.model.SingleJoke

fun SingleJoke.toJokeList(): List<Joke> = listOf(this.joke)
fun MultipleJoke.toJokeList(): List<Joke> = this.jokes