package com.android.mvi.di

import com.android.mvi.datasource.cache.database.DaoCharacter
import com.android.mvi.datasource.cache.mapper.CacheMapper
import com.android.mvi.datasource.network.mapper.NetworkMapper
import com.android.mvi.datasource.network.retrofit.ApiServiceRetrofit
import com.android.mvi.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        daoCharacter: DaoCharacter,
        retrofit: ApiServiceRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(daoCharacter, retrofit, cacheMapper, networkMapper)
    }
}
