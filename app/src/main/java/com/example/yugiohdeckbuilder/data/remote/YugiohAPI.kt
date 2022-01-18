package com.example.yugiohdeckbuilder.data.remote

import com.example.yugiohdeckbuilder.data.remote.dto.YugiohCard
import com.example.yugiohdeckbuilder.data.remote.dto.YugiohList
import retrofit2.http.GET
import retrofit2.http.Query

interface YugiohAPI {

    @GET("cardinfo.php")
    suspend fun getYugiohList(
        @Query("num") num: Int,
        @Query("offset") offset: Int
    ): YugiohList

    @GET("cardinfo.php")
    suspend fun getYugiohCardByName(
        @Query("name") cardName: String
    ): YugiohList

}