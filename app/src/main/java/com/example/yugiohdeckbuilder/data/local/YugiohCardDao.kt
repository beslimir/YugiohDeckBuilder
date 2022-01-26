package com.example.yugiohdeckbuilder.data.local

import androidx.room.*
import com.example.yugiohdeckbuilder.data.remote.dto.YugiohCard
import kotlinx.coroutines.flow.Flow

@Dao
interface YugiohCardDao {

    @Query("SELECT * FROM YugiohCard")
    fun getDeckList(): Flow<List<YugiohCard>>

    @Query("SELECT * FROM YugiohCard WHERE id = :cardId")
    suspend fun getCardById(cardId: Int): YugiohCard

    @Query("SELECT * FROM YugiohCard WHERE name = :cardName")
    suspend fun getCardByName(cardName: String): YugiohCard

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(yugiohCard: YugiohCard)

    @Delete
    suspend fun deleteCard(yugiohCard: YugiohCard)

}