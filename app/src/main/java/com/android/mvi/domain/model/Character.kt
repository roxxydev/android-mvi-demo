package com.android.mvi.domain.model

data class Character (
    var id: Int,
    var title: String,
    var body: String,
    var image: String,
    var category: String
): Model
