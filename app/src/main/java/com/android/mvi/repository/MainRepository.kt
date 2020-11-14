package com.android.mvi.repository

import com.android.mvi.datasource.cache.database.DaoCharacter
import com.android.mvi.datasource.cache.mapper.CacheMapper
import com.android.mvi.datasource.network.mapper.NetworkMapper
import com.android.mvi.datasource.network.retrofit.ApiServiceRetrofit
import com.android.mvi.domain.model.Character
import com.android.mvi.domain.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class  MainRepository
constructor(
    private val daoCharacter: DaoCharacter,
    private val apiService: ApiServiceRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {
    suspend fun getCharacters(): Flow<DataState<List<Character>>> = flow {
        emit(DataState.Loading)
        try {
            val networkCharacters = apiService.get()
            val characters = networkMapper.mapFromEntityList(networkCharacters)
            for (character in characters) {
                daoCharacter.insert(cacheMapper.mapToEntity(character))
            }
            val cacheCharacters = daoCharacter.get();
            emit(DataState.Success(cacheMapper.mapFromEntityList(cacheCharacters)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}
