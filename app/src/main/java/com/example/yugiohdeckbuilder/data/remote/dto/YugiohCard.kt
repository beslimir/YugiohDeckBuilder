package com.example.yugiohdeckbuilder.data.remote.dto


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class YugiohCard(
    val archetype: String?,
    val atk: Int?,
    val attribute: String?,
    @SerializedName("card_images")
    val cardImages: List<CardImage>?,
    @SerializedName("card_prices")
    val cardPrices: List<CardPrice>?,
    @SerializedName("card_sets")
    val cardSets: List<CardSet>?,
    val def: Int?,
    val desc: String,
    @PrimaryKey
    val id: Int,
    val level: Int?,
    val name: String,
    val race: String,
    val type: String
)