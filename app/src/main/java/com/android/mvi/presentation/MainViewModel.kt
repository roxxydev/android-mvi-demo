package com.android.mvi.presentation

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.android.mvi.domain.model.Character
import com.android.mvi.domain.state.DataState
import com.android.mvi.repository.MainRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel
@ViewModelInject
constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Character>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Character>>>
        get () = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when(mainStateEvent) {
                is MainStateEvent.GetCharacterEvents -> {
                    mainRepository.getCharacters()
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }

                is MainStateEvent.None -> {

                }
            }
        }
    }
}

sealed class MainStateEvent {

    object GetCharacterEvents: MainStateEvent()

    object None: MainStateEvent()
}
