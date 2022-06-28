package com.example.chunknorrisjokegallery.viewmodel

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chunknorrisjokegallery.interfaces.RepositoryInterface
import com.example.chunknorrisjokegallery.model.JokeConfig
import com.example.chunknorrisjokegallery.model.MainFragmentBundle
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

    val mainFragmentBundle: MainFragmentBundle = MainFragmentBundle()
    var jokeRvLayoutManagerInstance: Parcelable? = null

    // Check usage of clearViewData for info
    var needWipeViewData: Boolean = false

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

    fun onViewDestroyed() {
        _data.value = null
    }

    // Just a lazy way to make sure the outdated data doesn't stay,
    //      which is made possible in EditFragment.
    // Hurts the UX a lil' but it's worth it.
    fun wipeViewData() {
        jokeRvLayoutManagerInstance = null
        mainFragmentBundle.jokeList.clear()
        needWipeViewData = false
    }
}