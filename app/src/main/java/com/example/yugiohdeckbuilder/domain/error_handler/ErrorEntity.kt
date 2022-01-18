package com.example.yugiohdeckbuilder.domain.error_handler

sealed class ErrorEntity {
    object Network: ErrorEntity()
    object NotFound: ErrorEntity()
    object Unknown: ErrorEntity()
}
