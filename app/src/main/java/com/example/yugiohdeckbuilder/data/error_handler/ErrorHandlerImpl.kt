package com.example.yugiohdeckbuilder.data.error_handler

import android.util.Log
import com.example.yugiohdeckbuilder.domain.error_handler.ErrorEntity
import com.example.yugiohdeckbuilder.domain.error_handler.ErrorHandler
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import javax.inject.Inject

class ErrorHandlerImpl @Inject constructor(): ErrorHandler {

    override fun getError(throwable: Throwable): ErrorEntity {
        Log.d("aa", "Handler: ${throwable.localizedMessage}")
        return when(throwable) {
            is IOException -> ErrorEntity.Network
            is HttpException -> {
                when (throwable.code()) {
                    HttpURLConnection.HTTP_NOT_FOUND -> ErrorEntity.NotFound
                    else -> ErrorEntity.Unknown
                }
            }
            else -> ErrorEntity.Unknown
        }
    }
}