package com.example.yugiohdeckbuilder.data.repository

import com.example.yugiohdeckbuilder.data.local.YugiohDao
import com.example.yugiohdeckbuilder.data.remote.YugiohAPI
import com.example.yugiohdeckbuilder.data.remote.dto.YugiohCard
import com.example.yugiohdeckbuilder.data.remote.dto.YugiohList
import com.example.yugiohdeckbuilder.domain.error_handler.ErrorHandler
import com.example.yugiohdeckbuilder.domain.repository.YugiohRepository
import com.example.yugiohdeckbuilder.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class YugiohRepositoryImpl @Inject constructor(
    private val api: YugiohAPI,
    private val errorHandler: ErrorHandler,
    private val yugiohDao: YugiohDao
): YugiohRepository {

    /* API functions */

    override suspend fun getYugiohList(num: Int, offset: Int): Resource<YugiohList> {
        val response = try {
            api.getYugiohList(num, offset)
        } catch (throwable: Throwable) {
            return Resource.Error(errorHandler.getError(throwable))
        }

        return Resource.Success(response)
    }

    override suspend fun getYugiohCardsByName(name: String): Resource<YugiohList> {
        val response = try {
            api.getYugiohCardsByName(name)
        } catch (throwable: Throwable) {
            return Resource.Error(errorHandler.getError(throwable))
        }

        return Resource.Success(response)
    }

    override suspend fun testApi(attribute: String, num: Int, offset: Int): Resource<YugiohList> {
        val response = try {
            api.testApi(attribute, num, offset)
        } catch (throwable: Throwable) {
            return Resource.Error(errorHandler.getError(throwable))
        }

        return Resource.Success(response)
    }

    /* Room functions */

    override fun getDeckList(): Flow<List<YugiohCard>> = yugiohDao.getDeckList()

    override suspend fun getCardById(cardId: Int): YugiohCard = yugiohDao.getCardById(cardId)

    override suspend fun getCardByName(cardName: String): YugiohCard = yugiohDao.getCardByName(cardName)

    override suspend fun insertCard(yugiohCard: YugiohCard) {
        yugiohDao.insertCard(yugiohCard)
    }

    override suspend fun deleteCard(yugiohCard: YugiohCard) {
        yugiohDao.deleteCard(yugiohCard)
    }
}