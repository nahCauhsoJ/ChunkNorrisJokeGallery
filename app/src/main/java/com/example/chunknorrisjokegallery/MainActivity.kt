package com.example.chunknorrisjokegallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chunknorrisjokegallery.databinding.ActivityMainBinding
import com.example.chunknorrisjokegallery.view.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}