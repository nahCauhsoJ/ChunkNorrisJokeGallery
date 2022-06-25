package com.example.chunknorrisjokegallery.network

import com.example.chunknorrisjokegallery.interfaces.RepositoryInterface
import com.example.chunknorrisjokegallery.utils.toJokeList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: APIService
): RepositoryInterface {
    override fun getRandomOne(): Flow<Results> =
        flow {
            emit(Results.LOADING)
            try {
                val response = apiService.getRandomOne()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(Results.SUCCESS(it.toJokeList()))
                    } ?: throw java.lang.Exception("NOooo BOody")
                } else Exception(response.errorBody()?.string())
            } catch (e: java.lang.Exception) {
                emit(Results.ERROR(e))
            }
        }

    override fun getRandomMultiple(): Flow<Results> =
        flow {
            emit(Results.LOADING)
            try {
                val response = apiService.getRandomOne()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(Results.SUCCESS(it.toJokeList()))
                    } ?: throw java.lang.Exception("NOooo BOody")
                } else Exception(response.errorBody()?.string())
            } catch (e: java.lang.Exception) {
                emit(Results.ERROR(e))
            }
        }
}