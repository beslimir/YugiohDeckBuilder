package com.example.yugiohdeckbuilder.data.repository

import com.example.yugiohdeckbuilder.data.remote.YugiohAPI
import com.example.yugiohdeckbuilder.data.remote.dto.YugiohCard
import com.example.yugiohdeckbuilder.data.remote.dto.YugiohList
import com.example.yugiohdeckbuilder.domain.error_handler.ErrorEntity
import com.example.yugiohdeckbuilder.domain.error_handler.ErrorHandler
import com.example.yugiohdeckbuilder.domain.repository.YugiohRepository
import com.example.yugiohdeckbuilder.util.Resource
import java.lang.Exception
import javax.inject.Inject

class YugiohRepositoryImpl @Inject constructor(
    private val api: YugiohAPI,
    private val errorHandler: ErrorHandler
): YugiohRepository {

    override suspend fun getYugiohList(num: Int, offset: Int): Resource<YugiohList> {
        val response = try {
            api.getYugiohList(num, offset)
        } catch (throwable: Throwable) {
            return Resource.Error(errorHandler.getError(throwable))
        }

        return Resource.Success(response)
    }

    override suspend fun getYugiohCardByName(name: String): Resource<YugiohCard> {
        val response = try {
            api.getYugiohCardByName(name)
        } catch (throwable: Throwable) {
            return Resource.Error(errorHandler.getError(throwable))
        }

        return Resource.Success(response)
    }
}