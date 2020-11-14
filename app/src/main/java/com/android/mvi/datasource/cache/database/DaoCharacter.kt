package com.android.mvi.datasource.cache.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.mvi.datasource.cache.model.EntityCacheCharacter

@Dao
interface DaoCharacter {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entityCacheCharacter: EntityCacheCharacter): Long

    @Query("SELECT * FROM character")
    suspend fun get(): List<EntityCacheCharacter>
}
