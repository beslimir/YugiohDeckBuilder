package com.example.yugiohdeckbuilder.util

import com.example.yugiohdeckbuilder.domain.error_handler.ErrorEntity

sealed class Resource<T>(val apiData: T? = null, val errorMessage: ErrorEntity? = null) {
    class Success<T>(apiData: T?): Resource<T>(apiData)
    class Error<T>(errorMessage: ErrorEntity?, data: T? = null): Resource<T>(data, errorMessage)
    class Loading<T>(apiData: T? = null): Resource<T>(apiData)
}