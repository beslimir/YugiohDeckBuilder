package com.example.yugiohdeckbuilder.domain.repository

import com.example.yugiohdeckbuilder.data.remote.dto.YugiohCard
import com.example.yugiohdeckbuilder.data.remote.dto.YugiohList
import com.example.yugiohdeckbuilder.util.Resource
import kotlinx.coroutines.flow.Flow

interface YugiohRepository {

    /* API functions */

    suspend fun getYugiohList(num: Int, offset: Int): Resource<YugiohList>

    suspend fun getYugiohCardsByName(name: String): Resource<YugiohList>

    suspend fun testApi(attribute: String, num: Int, offset: Int): Resource<YugiohList>

    /* Room functions */

    fun getDeckList(): Flow<List<YugiohCard>>

    suspend fun getCardById(cardId: Int): YugiohCard

    suspend fun getCardByName(cardName: String): YugiohCard

    suspend fun insertCard(yugiohCard: YugiohCard)

    suspend fun deleteCard(yugiohCard: YugiohCard)

}