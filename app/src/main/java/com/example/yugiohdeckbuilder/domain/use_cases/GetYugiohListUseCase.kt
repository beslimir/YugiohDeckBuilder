package com.example.yugiohdeckbuilder.domain.use_cases

import com.example.yugiohdeckbuilder.domain.repository.YugiohRepository

class GetYugiohListUseCase(
    private val yugiohRepository: YugiohRepository
) {
}