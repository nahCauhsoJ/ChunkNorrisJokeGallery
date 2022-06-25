package com.example.chunknorrisjokegallery.utils

import android.app.AlertDialog
import android.content.Context

class JokeBox {
    companion object {
        fun Speak(context: Context, message: String) {
            AlertDialog.Builder(context)
                .setTitle("Ok, so...")
                .setMessage(message)
                .setPositiveButton("Dismiss", null)
                .show()
        }
    }
}