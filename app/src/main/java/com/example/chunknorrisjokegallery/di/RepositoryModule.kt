@file:Suppress("unused")

package com.example.chunknorrisjokegallery.di

import com.example.chunknorrisjokegallery.interfaces.RepositoryInterface
import com.example.chunknorrisjokegallery.network.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun repository(repository: Repository): RepositoryInterface

}