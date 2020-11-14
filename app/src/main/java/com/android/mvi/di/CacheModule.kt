package com.android.mvi.di

import android.content.Context
import androidx.room.Room
import com.android.mvi.datasource.cache.database.DaoCharacter
import com.android.mvi.datasource.cache.database.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object CacheModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            Database.DATBASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDaoCharacter(database: Database): DaoCharacter {
        return database.daoCharacter()
    }
}
