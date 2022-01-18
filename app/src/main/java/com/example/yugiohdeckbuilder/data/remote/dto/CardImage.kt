package com.example.yugiohdeckbuilder.data.remote.dto


import com.google.gson.annotations.SerializedName

data class CardImage(
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("image_url_small")
    val imageUrlSmall: String
)