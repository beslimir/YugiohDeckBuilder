package com.example.yugiohdeckbuilder.presentation.home_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yugiohdeckbuilder.data.remote.dto.YugiohCard
import com.example.yugiohdeckbuilder.data.remote.dto.YugiohList
import com.example.yugiohdeckbuilder.domain.repository.YugiohRepository
import com.example.yugiohdeckbuilder.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: YugiohRepository,
) : ViewModel() {

    var featuredList = mutableStateOf<List<YugiohCard>>(listOf())
    var currentCardShown = mutableStateOf(0)
    var featuredUrl = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var loadError = mutableStateOf(null)

    var isFeaturedCardLoading = mutableStateOf(true)

    init {
        getYugiohList(30, 0)
    }

    suspend fun getYugiohCardByName(): Resource<YugiohList> {
        return repository.getYugiohCardByName("Jinzo")
    }

    fun getYugiohList(num: Int, offset: Int) {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getYugiohList(num, offset)

            when (result) {
                is Resource.Success -> {
                    val yugiohCards = result.apiData?.data!!.map {
                        YugiohCard(
                            archetype = it.archetype,
                            atk = it.atk,
                            attribute = it.attribute,
                            cardImages = it.cardImages,
                            cardPrices = it.cardPrices,
                            cardSets = it.cardSets,
                            def = it.def,
                            desc = it.desc,
                            id = it.id,
                            level = it.level,
                            name = it.name,
                            race = it.race,
                            type = it.type
                        )
                    }

                    isLoading.value = false
                    loadError.value = null
                    featuredList.value += yugiohCards
                }
                is Resource.Error -> {
                    //TODO: Fix this
                    loadError.value = null
                }
            }
            if (featuredList.value.isNotEmpty()) {
                featuredUrl.value = featuredList.value[0].cardImages!![0].imageUrl
            }
        }
    }

    fun getCurrentShownCard(command: String) {
        isFeaturedCardLoading.value = true

        if (command == "right") {
            if (currentCardShown.value == featuredList.value.size - 1) {
                currentCardShown.value = 0
            } else {
                currentCardShown.value += 1
            }
        } else {
            if (currentCardShown.value == 0) {
                currentCardShown.value = featuredList.value.size - 1
            } else {
                currentCardShown.value -= 1
            }
        }

        featuredUrl.value = featuredList.value[currentCardShown.value].cardImages!![0].imageUrl
    }

}