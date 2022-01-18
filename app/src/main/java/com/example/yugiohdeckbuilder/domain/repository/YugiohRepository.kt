package com.example.yugiohdeckbuilder.domain.repository

import com.example.yugiohdeckbuilder.data.remote.dto.YugiohCard
import com.example.yugiohdeckbuilder.data.remote.dto.YugiohList
import com.example.yugiohdeckbuilder.util.Resource

interface YugiohRepository {

    suspend fun getYugiohList(num: Int, offset: Int): Resource<YugiohList>

    suspend fun getYugiohCardByName(name: String): Resource<YugiohCard>
}