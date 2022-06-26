package com.example.chunknorrisjokegallery.interfaces

import com.example.chunknorrisjokegallery.network.Results
import kotlinx.coroutines.flow.Flow

interface RepositoryInterface {
    fun getRandomOne(
        first_name: String?,
        last_name: String?,
        exclude_category: List<String>?
    ): Flow<Results>
    fun getRandomMultiple(
        first_name: String?,
        last_name: String?,
        exclude_category: List<String>?
    ): Flow<Results>
}