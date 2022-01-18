package com.example.yugiohdeckbuilder.domain.error_handler

interface ErrorHandler {

    fun getError(throwable: Throwable): ErrorEntity

}