package com.example.yugiohdeckbuilder.di

import android.app.Application
import androidx.room.Room
import com.example.yugiohdeckbuilder.data.error_handler.ErrorHandlerImpl
import com.example.yugiohdeckbuilder.data.local.YugiohDatabase
import com.example.yugiohdeckbuilder.data.remote.YugiohAPI
import com.example.yugiohdeckbuilder.data.repository.YugiohRepositoryImpl
import com.example.yugiohdeckbuilder.domain.error_handler.ErrorHandler
import com.example.yugiohdeckbuilder.domain.repository.YugiohRepository
import com.example.yugiohdeckbuilder.util.Constants.BASE_URL
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideYugiohAPI(client: OkHttpClient): YugiohAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
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
    fun provideYugiohDatabase(app: Application): YugiohDatabase = Room.databaseBuilder(
        app,
        YugiohDatabase::class.java,
        "yugioh_database"
    ).build()

    @Provides
    @Singleton
    fun provideYugiohRepository(api: YugiohAPI, errorHandler: ErrorHandler, db: YugiohDatabase): YugiohRepository {
        return YugiohRepositoryImpl(api, errorHandler, db.yugiohDao)
    }

}