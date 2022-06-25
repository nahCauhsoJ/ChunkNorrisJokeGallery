package com.example.chunknorrisjokegallery.interfaces

import com.example.chunknorrisjokegallery.network.Results
import kotlinx.coroutines.flow.Flow

interface RepositoryInterface {
    fun getRandomOne(): Flow<Results>
    fun getRandomMultiple(): Flow<Results>
}