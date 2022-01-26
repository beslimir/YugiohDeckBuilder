package com.example.yugiohdeckbuilder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.yugiohdeckbuilder.data.remote.dto.YugiohCard
import com.example.yugiohdeckbuilder.util.TypeConverter

@Database(
    entities = [YugiohCard::class],
    version = 1
)
@TypeConverters(TypeConverter::class)
abstract class YugiohDatabase: RoomDatabase() {

    abstract val yugiohDao: YugiohDao

}