package com.android.mvi.datasource.cache.mapper

import com.android.mvi.datasource.cache.model.EntityCacheCharacter
import com.android.mvi.datasource.cache.model.EntityCache
import com.android.mvi.domain.util.MapperEntity
import com.android.mvi.domain.model.Character
import com.android.mvi.domain.model.Model
import javax.inject.Inject

class CacheMapper
@Inject
constructor(): MapperEntity<EntityCache, Model> {

    override fun mapFromEntity(entityCache: EntityCache): Character {

        val characterCache = entityCache as EntityCacheCharacter

        return Character(
            id = characterCache.id,
            title = characterCache.title,
            body = characterCache.body,
            category = characterCache.category,
            image = characterCache.image
        )
    }

    override fun mapToEntity(domainModel: Model): EntityCacheCharacter {

        var character = domainModel as Character

        return EntityCacheCharacter(
            id = character.id,
            title = character.title,
            body = character.body,
            category = character.category,
            image = character.image
        )
    }

    fun mapFromEntityList(entities: List<EntityCacheCharacter>): List<Character> {

        return entities.map{ mapFromEntity(it) }
    }
}
