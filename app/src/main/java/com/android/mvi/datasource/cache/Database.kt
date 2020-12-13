package com.android.mvi.datasource.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.mvi.datasource.cache.DaoCharacter
import com.android.mvi.datasource.model.EntityCacheCharacter

@Database(entities = [EntityCacheCharacter::class], version = 1)
abstract class Database: RoomDatabase() {

    abstract fun daoCharacter(): DaoCharacter

    companion object {

        val DATBASE_NAME: String = "db_app"
    }
}
