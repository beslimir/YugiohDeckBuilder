package com.example.yugiohdeckbuilder.di

import com.example.yugiohdeckbuilder.data.error_handler.ErrorHandlerImpl
import com.example.yugiohdeckbuilder.data.remote.YugiohAPI
import com.example.yugiohdeckbuilder.data.repository.YugiohRepositoryImpl
import com.example.yugiohdeckbuilder.domain.error_handler.ErrorHandler
import com.example.yugiohdeckbuilder.domain.repository.YugiohRepository
import com.example.yugiohdeckbuilder.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideYugiohAPI(): YugiohAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(YugiohAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideErrorHandler(): ErrorHandler {
        return ErrorHandlerImpl()
    }

    @Provides
    @Singleton
    fun provideYugiohRepository(api: YugiohAPI, errorHandler: ErrorHandler): YugiohRepository {
        return YugiohRepositoryImpl(api, errorHandler)
    }

}