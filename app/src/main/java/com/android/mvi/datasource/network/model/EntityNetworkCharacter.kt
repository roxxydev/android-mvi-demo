package com.android.mvi.datasource.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EntityNetworkCharacter(

    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("title")
    @Expose
    var title: String,

    @SerializedName("body")
    @Expose
    var body: String,

    @SerializedName("category")
    @Expose
    var category: String,

    @SerializedName("image")
    @Expose
    var image: String
): EntityNetwork
