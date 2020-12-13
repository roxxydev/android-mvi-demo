package com.android.mvi.datasource.mapper

import com.android.mvi.datasource.model.EntityCacheCharacter
import com.android.mvi.datasource.model.EntityModel
import com.android.mvi.datasource.model.EntityNetworkCharacter
import com.android.mvi.domain.model.Character
import javax.inject.Inject

class CharacterMapper
@Inject
constructor(): MapperEntity<EntityModel, Character> {

    override fun mapFromCacheEntity(entity: EntityModel): Character {
        val entityCacheCharacter = entity as EntityCacheCharacter

        return Character(
            id = entityCacheCharacter.id,
            title = entityCacheCharacter.title,
            body = entityCacheCharacter.body,
            category = entityCacheCharacter.category,
            image = entityCacheCharacter.image
        )
    }

    override fun mapToCacheEntity(character: Character): EntityModel {
        return EntityCacheCharacter(
            id = character.id,
            title = character.title,
            body = character.body,
            category = character.category,
            image = character.image
        )
    }

    override fun mapFromNetworkEntity(entity: EntityModel): Character {
        val entityNetworkCharacter = entity as EntityNetworkCharacter

        return Character(
            id = entityNetworkCharacter.id,
            title = entityNetworkCharacter.title,
            category = entityNetworkCharacter.category,
            body = entityNetworkCharacter.body,
            image = entityNetworkCharacter.image
        )
    }

    override fun mapToNetworkEntity(character: Character): EntityModel {

        return EntityNetworkCharacter(
            id = character.id,
            title = character.title,
            category = character.category,
            body = character.body,
            image = character.image
        )
    }

    fun mapFromNetworkEntityList(entities: List<EntityNetworkCharacter>): List<Character> {

        return entities.map {
            mapFromNetworkEntity(it)
        }
    }

    fun mapFromCacheEntityList(entities: List<EntityCacheCharacter>): List<Character> {

        return entities.map{ mapFromCacheEntity(it) }
    }

    fun mapToCacheEntityList(characters: List<Character>): List<EntityModel> {

        return characters.map{ mapToCacheEntity(it) }
    }
}