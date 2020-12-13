package com.android.mvi.presentation.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.android.mvi.domain.model.Character
import com.android.mvi.domain.state.DataState
import com.android.mvi.repository.MainRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainViewModel
@ViewModelInject
constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<MainDataState>> = MutableLiveData()

    val dataState: LiveData<DataState<MainDataState>>
        get () = _dataState

    fun setStateEvent(intent: MainIntent) {
        viewModelScope.launch {
            when(intent) {
                is MainIntent.GetCharactersIntent -> {
                    mainRepository.getCharacters()
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
                is MainIntent.None -> {
                }
            }
        }
    }
}

sealed class MainIntent {
    object GetCharactersIntent: MainIntent()
    object None : MainIntent()
}

class MainDataState(
    val characters: List<Character>,
    val cacheCharacters: List<Character>
)

