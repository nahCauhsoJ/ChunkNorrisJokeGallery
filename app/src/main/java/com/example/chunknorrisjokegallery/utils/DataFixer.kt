package com.example.chunknorrisjokegallery.utils

fun String.fixSpecialChars(): String =
    this.replace("&quot;","\"")