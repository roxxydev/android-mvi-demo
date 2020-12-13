package com.android.mvi.datasource.model

data class EntityNetworkCharacter(
    var id: Int,
    var title: String,
    var body: String,
    var category: String,
    var image: String
): EntityModel
