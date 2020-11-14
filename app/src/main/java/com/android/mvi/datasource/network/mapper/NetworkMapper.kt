package com.android.mvi.datasource.network.mapper

import com.android.mvi.datasource.network.model.EntityNetwork
import com.android.mvi.datasource.network.model.EntityNetworkCharacter
import com.android.mvi.domain.util.MapperEntity
import com.android.mvi.domain.model.Character
import com.android.mvi.domain.model.Model
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() : MapperEntity<EntityNetwork, Model>{

    override fun mapFromEntity(entityNetwork: EntityNetwork): Character {

        val entityNetworkCharacter = entityNetwork as EntityNetworkCharacter

        return Character(
            id = entityNetworkCharacter.id,
            title = entityNetworkCharacter.title,
            category = entityNetworkCharacter.category,
            body = entityNetworkCharacter.body,
            image = entityNetworkCharacter.image
        )
    }

    override fun mapToEntity(domainModel: Model): EntityNetworkCharacter {

        val character = domainModel as Character

        return EntityNetworkCharacter(
            id = character.id,
            title = character.title,
            category = character.category,
            body = character.body,
            image = character.image
        )
    }

    fun mapFromEntityList(entities: List<EntityNetworkCharacter>): List<Character> {
        return entities.map { mapFromEntity(it) }
    }
}
