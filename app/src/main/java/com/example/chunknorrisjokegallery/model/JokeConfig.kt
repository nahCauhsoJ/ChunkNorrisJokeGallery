package com.example.chunknorrisjokegallery.model

data class JokeConfig(
    var first_name: String? = null,
    var last_name: String? = null,
    var no_explicit: Boolean = false,
    var no_nerdy: Boolean = false
) {
    fun getExcludeList(): List<String>? {
        val lst: MutableList<String> = mutableListOf()
        if (no_explicit) lst.add("explicit")
        if (no_nerdy) lst.add("nerdy")

        return lst.ifEmpty{null}
    }
}
