package com.example.chunknorrisjokegallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.chunknorrisjokegallery.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.nav_container)
        setupActionBarWithNavController(navController, AppBarConfiguration(navController.graph))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_btn -> findNavController(R.id.nav_container)
                .navigate(R.id.action_mainFragment_to_editFragment)
            else -> findNavController(R.id.nav_container).navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }
}