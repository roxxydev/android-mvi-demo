package com.android.mvi.domain.state

sealed class DataState<T>(
    var loading: Boolean,
    var data: T? = null,
    var stateMessage: StateMessage? = null
) {
    class LOADING<T>(
        isLoading: Boolean,
        cachedData: T? = null
    ) : DataState<T>(
        loading = isLoading,
        data = cachedData
    )

    class SUCCESS<T>(
        data: T? = null,
        stateMessage: StateMessage? = null
    ) : DataState<T>(
        loading = false,
        data = data,
        stateMessage = stateMessage
    )

    class ERROR<T>(
        stateMessage: StateMessage,
        cachedData: T? = null
    ) : DataState<T>(
        loading = false,
        data = cachedData,
        stateMessage = stateMessage
    )
}

sealed class MessageType {
    object SUCCESS : MessageType()
    object ERROR : MessageType()
    object INFO : MessageType()
    object NONE : MessageType()
}

data class StateMessage(
    val message: String?,
    val messageType: MessageType
)