package com.android.mvi.di

import android.content.Context
import com.android.mvi.datasource.cache.DaoCharacter
import com.android.mvi.datasource.mapper.CharacterMapper
import com.android.mvi.datasource.network.ApiServiceRetrofit
import com.android.mvi.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        @ApplicationContext appContext: Context,
        daoCharacter: DaoCharacter,
        retrofit: ApiServiceRetrofit,
        characterMapper: CharacterMapper
    ): MainRepository {
        return MainRepository(appContext, daoCharacter, retrofit, characterMapper)
    }
}
