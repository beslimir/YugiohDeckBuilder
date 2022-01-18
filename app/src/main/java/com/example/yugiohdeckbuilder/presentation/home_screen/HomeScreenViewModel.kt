package com.example.yugiohdeckbuilder.presentation.home_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yugiohdeckbuilder.data.remote.dto.YugiohCard
import com.example.yugiohdeckbuilder.data.remote.dto.YugiohList
import com.example.yugiohdeckbuilder.domain.repository.YugiohRepository
import com.example.yugiohdeckbuilder.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: YugiohRepository
): ViewModel() {

    var featuredList = mutableStateOf<List<YugiohCard>>(listOf())
    var currentCardShown = mutableStateOf(2)



    init {
        getFeaturedCards("Water")
    }


    suspend fun getYugiohCardByName(): Resource<YugiohList> {
        return repository.getYugiohCardByName("Jinzoo")
    }

    suspend fun getYugiohList(): Resource<YugiohList> {
        return repository.getYugiohList(num = 5, offset = 0)
    }

    fun getCurrentShownCard(command: String) {
        if (command == "right") {
            if (currentCardShown.value < 3) {
                currentCardShown.value += 1
            } else {
                currentCardShown.value = 1
            }
        } else {
            if (currentCardShown.value > 1) {
                currentCardShown.value -= 1
            } else {
                currentCardShown.value = 3
            }
        }
    }

    //TODO: Change later yugiohList fo attribute list
    fun getFeaturedCards(attribute: String) {
        viewModelScope.launch(Dispatchers.Default) {
            val resultCardList = repository.getYugiohList(5, 0)
            when (resultCardList) {
                is Resource.Success -> {
                    for (element in resultCardList.apiData?.data!!) {
                        featuredList.value += element
                    }
                }
                else -> {

                }
            }
        }

    }

}