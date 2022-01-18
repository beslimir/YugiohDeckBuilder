package com.example.yugiohdeckbuilder.util

import com.example.yugiohdeckbuilder.domain.error_handler.ErrorEntity

sealed class Resource<T>(val data: T? = null, val errorMessage: ErrorEntity? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(errorMessage: ErrorEntity?, data: T? = null): Resource<T>(data, errorMessage)
    class Loading<T>(data: T? = null): Resource<T>(data)
}