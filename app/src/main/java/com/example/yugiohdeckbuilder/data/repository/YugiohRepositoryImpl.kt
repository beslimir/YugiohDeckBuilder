package com.example.yugiohdeckbuilder.data.repository

import com.example.yugiohdeckbuilder.data.remote.YugiohAPI
import com.example.yugiohdeckbuilder.data.remote.dto.YugiohCard
import com.example.yugiohdeckbuilder.data.remote.dto.YugiohList
import com.example.yugiohdeckbuilder.domain.repository.YugiohRepository
import com.example.yugiohdeckbuilder.util.Resource
import java.lang.Exception
import javax.inject.Inject

class YugiohRepositoryImpl @Inject constructor(
    private val api: YugiohAPI
): YugiohRepository {

    override suspend fun getYugiohList(num: Int, offset: Int): Resource<YugiohList> {
        val response = try {
            api.getYugiohList(num, offset)
        } catch (e: Exception) {
            return Resource.Error(e.localizedMessage ?: "An unexpected error occurred while loading Yugioh list")
        }

        return Resource.Success(response)
    }

    override suspend fun getYugiohCardByName(name: String): Resource<YugiohCard> {
        val response = try {
            api.getYugiohCardByName(name)
        } catch (e: Exception) {
            return Resource.Error(e.localizedMessage ?: "An unexpected error occurred while loading Yugioh card")
        }

        return Resource.Success(response)
    }
}