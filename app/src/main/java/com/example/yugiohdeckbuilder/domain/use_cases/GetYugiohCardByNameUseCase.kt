package com.example.yugiohdeckbuilder.domain.use_cases

import com.example.yugiohdeckbuilder.domain.repository.YugiohRepository

class GetYugiohCardByNameUseCase(
    private val yugiohRepository: YugiohRepository
) {

    suspend operator fun invoke(name: String) {
        yugiohRepository.getYugiohCardByName(name)
    }

}