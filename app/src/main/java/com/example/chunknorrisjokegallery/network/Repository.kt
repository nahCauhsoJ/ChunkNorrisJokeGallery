package com.example.chunknorrisjokegallery.network

import com.example.chunknorrisjokegallery.interfaces.RepositoryInterface
import com.example.chunknorrisjokegallery.utils.toJokeList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: APIService
): RepositoryInterface {
    override fun getRandomOne(
        first_name: String?,
        last_name: String?,
        exclude_category: List<String>?
    ): Flow<Results> =
        flow {
            emit(Results.LOADING)
            try {
                val response = apiService.getRandomOne(
                    exclude_category = exclude_category,
                    first_name = first_name,
                    last_name = last_name
                )
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(Results.SUCCESS(it.toJokeList()))
                    } ?: throw java.lang.Exception("Lacking a body...")
                } else throw Exception(response.errorBody()?.toString())
            } catch (e: java.lang.Exception) {
                emit(Results.ERROR(e))
            }
        }

    override fun getRandomMultiple(
        first_name: String?,
        last_name: String?,
        exclude_category: List<String>?
    ): Flow<Results> =
        flow {
            emit(Results.LOADING)
            try {
                val response = apiService.getRandomMultiple(
                    exclude_category = exclude_category,
                    first_name = first_name,
                    last_name = last_name
                )
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(Results.SUCCESS(it.toJokeList()))
                    } ?: throw Exception("Lacking a body...")
                } else throw Exception(response.errorBody()?.toString())
            } catch (e: java.lang.Exception) {
                emit(Results.ERROR(e))
            }
        }
}