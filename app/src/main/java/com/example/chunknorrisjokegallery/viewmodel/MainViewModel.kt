package com.example.chunknorrisjokegallery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chunknorrisjokegallery.interfaces.RepositoryInterface
import com.example.chunknorrisjokegallery.model.JokeConfig
import com.example.chunknorrisjokegallery.network.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: RepositoryInterface,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _data: MutableLiveData<Results> = MutableLiveData()
    val data: LiveData<Results> get() = _data
    val jokeConfig: JokeConfig = JokeConfig()

    fun getRandomOne() {
        viewModelScope.launch {
            repository.getRandomOne(
                exclude_category = jokeConfig.getExcludeList(),
                first_name = jokeConfig.first_name,
                last_name = jokeConfig.last_name
            )
                .flowOn(ioDispatcher)
                .collect { _data.postValue(it) }
        }
    }

    fun getRandomMultiple() {
        viewModelScope.launch {
            repository.getRandomMultiple(
                exclude_category = jokeConfig.getExcludeList(),
                first_name = jokeConfig.first_name,
                last_name = jokeConfig.last_name
            )
                .flowOn(ioDispatcher)
                .collect { _data.postValue(it) }
        }
    }
}