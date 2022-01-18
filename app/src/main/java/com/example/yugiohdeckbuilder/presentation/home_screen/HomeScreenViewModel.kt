package com.example.yugiohdeckbuilder.presentation.home_screen

import androidx.lifecycle.ViewModel
import com.example.yugiohdeckbuilder.data.remote.dto.YugiohList
import com.example.yugiohdeckbuilder.domain.repository.YugiohRepository
import com.example.yugiohdeckbuilder.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: YugiohRepository
): ViewModel() {

    suspend fun getYugiohCardByName(): Resource<YugiohList> {
        return repository.getYugiohCardByName("Jinzoo")
    }

}