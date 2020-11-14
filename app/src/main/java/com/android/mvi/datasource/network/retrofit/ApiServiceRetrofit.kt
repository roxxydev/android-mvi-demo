package com.android.mvi.datasource.network.retrofit

import com.android.mvi.datasource.network.model.EntityNetworkCharacter
import retrofit2.http.GET

interface ApiServiceRetrofit {

    // API endpoint
    @GET("5fafc6933abee46e2438b519")
    suspend fun get(): List<EntityNetworkCharacter>
}
