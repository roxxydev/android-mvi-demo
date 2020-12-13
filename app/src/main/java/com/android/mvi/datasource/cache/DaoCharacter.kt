package com.android.mvi.datasource.cache

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.android.mvi.datasource.model.EntityCacheCharacter

@Dao
interface DaoCharacter:
    BaseDao<EntityCacheCharacter> {

    // Order cache in descending so that cache items will be displayed starting from recent search.
    @Query("SELECT * FROM character ORDER BY id DESC LIMIT 50")
    suspend fun getAll(): List<EntityCacheCharacter>

    @Query("SELECT * FROM character WHERE id=:id")
    suspend fun get(id: Int?): EntityCacheCharacter

    @Transaction
    suspend fun upsert(obj: EntityCacheCharacter) {
        val id = insert(obj)
        if (id == -1L) {
            update(obj)
        }
    }

    @Transaction
    suspend fun upsert(objList: List<EntityCacheCharacter>) {
        val insertResult: List<Long> = insert(objList)
        val updateList: MutableList<EntityCacheCharacter> = ArrayList()
        for (i in insertResult.indices) if (insertResult[i] == -1L) {
            updateList.add(objList[i])
        }
        if (updateList.isNotEmpty()) {
            update(updateList)
        }
    }
}
