package com.example.yugiohdeckbuilder.util

import androidx.room.TypeConverter
import com.example.yugiohdeckbuilder.data.remote.dto.CardImage
import com.example.yugiohdeckbuilder.data.remote.dto.CardPrice
import com.example.yugiohdeckbuilder.data.remote.dto.CardSet
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class TypeConverter {

    @TypeConverter
    fun fromCardImageListToString(list: List<CardImage?>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromStringToCardImageList(string: String?): List<CardImage?>? {
        val listType: Type = object: TypeToken<List<CardImage?>?>(){}.type
        return Gson().fromJson<List<CardImage?>>(string, listType)
    }

    @TypeConverter
    fun fromCardPriceListToString(list: List<CardPrice?>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromStringToCardPriceList(string: String?): List<CardPrice?>? {
        val listType: Type = object: TypeToken<List<CardPrice?>?>(){}.type
        return Gson().fromJson<List<CardPrice?>>(string, listType)
    }

    @TypeConverter
    fun fromCardSetListToString(list: List<CardSet?>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromStringToCardSetList(string: String?): List<CardSet?>? {
        val listType: Type = object: TypeToken<List<CardSet?>?>(){}.type
        return Gson().fromJson<List<CardSet?>>(string, listType)
    }



}