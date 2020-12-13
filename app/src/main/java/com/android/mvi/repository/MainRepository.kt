package com.android.mvi.repository

import android.content.Context
import com.android.mvi.R
import com.android.mvi.datasource.cache.DaoCharacter
import com.android.mvi.datasource.mapper.CharacterMapper
import com.android.mvi.datasource.model.EntityCacheCharacter
import com.android.mvi.datasource.model.EntityNetworkCharacter
import com.android.mvi.datasource.network.ApiServiceRetrofit
import com.android.mvi.datasource.network.ResultWrapper
import com.android.mvi.datasource.network.safeApiCall
import com.android.mvi.domain.model.Character
import com.android.mvi.domain.state.DataState
import com.android.mvi.domain.state.DataState.LOADING
import com.android.mvi.domain.state.MessageType
import com.android.mvi.domain.state.StateMessage
import com.android.mvi.presentation.main.MainDataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class  MainRepository
constructor(
    private val appContext: Context,
    private val daoCharacter: DaoCharacter,
    private val apiService: ApiServiceRetrofit,
    private val mapper: CharacterMapper
) {

    suspend fun getCharacters()
            : Flow<DataState<MainDataState>> = flow {

        try {
            emit(LOADING(true))

            val response = safeApiCall {
                apiService.get()
            }

            when(response) {
                is ResultWrapper.Success<List<EntityNetworkCharacter>> -> {

                    val networkDto = response.value
                    val characterDto: List<Character> = mapper.mapFromNetworkEntityList(networkDto)
                    val dtoToCache = mapper.mapToCacheEntityList(characterDto)

                    daoCharacter.upsert(dtoToCache as List<EntityCacheCharacter>)

                    val cacheDao = daoCharacter.getAll()
                    val characterDao = mapper.mapFromCacheEntityList(cacheDao)

                    emit(
                        DataState.SUCCESS(
                            MainDataState(characterDto, characterDao)
                        )
                    )
                }
                else -> {
                    // Emit error with message if network issue or error response
                    val errorTxt = appContext.getString(R.string.error_network)
                    val stateMsg = StateMessage(errorTxt, MessageType.ERROR)

                    val cacheData = daoCharacter.getAll()
                    val contentsCache = mapper.mapFromCacheEntityList(cacheData)

                    emit(
                        DataState.ERROR(
                            stateMsg,
                            MainDataState(emptyList(), contentsCache)
                        )
                    )
                }
            }

            emit(LOADING(false))

        } catch (e: Exception) {
            Timber.e(e)
            val errorTxt = appContext.getString(R.string.error_message)
            val stateMsg = StateMessage(errorTxt, MessageType.ERROR)
            emit(DataState.ERROR(stateMsg))
        }
    }
}
