package com.example.yugiohdeckbuilder.presentation.home_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yugiohdeckbuilder.data.remote.dto.YugiohCard
import com.example.yugiohdeckbuilder.domain.error_handler.ErrorEntity
import com.example.yugiohdeckbuilder.domain.repository.YugiohRepository
import com.example.yugiohdeckbuilder.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * getRowBorder - holds the currently clicked row index
 * deckList - holds the list of cards saved in our deck
 *
 * */

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: YugiohRepository
) : ViewModel() {

    /* API variables */

    var featuredList = mutableStateOf<List<YugiohCard>>(listOf())
    var currentCardShown = mutableStateOf(0)
    var featuredUrl = mutableStateOf("")
    var loadError = mutableStateOf("")
    var isSearchbarVisible = mutableStateOf(false)
    var searchValue = mutableStateOf("Search...")
    var searchBarHintDeleted = mutableStateOf(false)
    var getRowBorder = mutableStateOf(-1)

    /* Room variables */

    private var getDeckJob: Job? = null
    var deckList = mutableStateOf<List<Int>>(listOf())
    
    init {
        getYugiohList(30, 0)
        getDeckList()
    }

    /* API functions */

    fun getYugiohCardsByName(name: String) {
        if (name.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.Default) {
                delay(500L)
                val searchedCards = repository.getYugiohCardsByName(name)
                when (searchedCards) {
                    is Resource.Success -> {
                        getRowBorder.value = -1 //reset rowBorder value
                        featuredList.value =
                            listOf() //reset featuredList (the one we see on starting app)
                        currentCardShown.value = 0 //reset the featured shown card to first card (0)
                        val yugiohCards = searchedCards.apiData?.data!!.map {
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
                        loadError.value = ""
                        featuredList.value += yugiohCards
                    }
                    is Resource.Error -> {
                        when (searchedCards.errorMessage) {
                            is ErrorEntity.Unknown -> {
                                loadError.value = "Unknown Error"
                            }
                            is ErrorEntity.Network -> {
                                loadError.value = "Network Issue"
                            }
                            is ErrorEntity.NotFound -> {
                                loadError.value = "Error not found"
                            }
                        }
                    }
                    is Resource.Loading -> {

                    }
                }
                if (featuredList.value.isNotEmpty()) {
                    featuredUrl.value = featuredList.value[0].cardImages!![0].imageUrl
                }
            }
        } else {
            getYugiohList(30, 0)
        }
    }

    fun getYugiohList(num: Int, offset: Int) {
        viewModelScope.launch {
            val result = repository.getYugiohList(num, offset)
            when (result) {
                is Resource.Success -> {
                    getRowBorder.value = -1 //reset rowBorder value
                    featuredList.value =
                        listOf() //reset featuredList (the one we see on starting app)
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

                    loadError.value = ""
                    featuredList.value += yugiohCards
                }
                is Resource.Error -> {
                    when (result.errorMessage) {
                        is ErrorEntity.Unknown -> {
                            loadError.value = "Unknown Error"
                        }
                        is ErrorEntity.Network -> {
                            loadError.value = "Network Issue"
                        }
                        is ErrorEntity.NotFound -> {
                            loadError.value = "Error not found"
                        }
                    }
                }
            }
            if (featuredList.value.isNotEmpty()) {
                featuredUrl.value = featuredList.value[0].cardImages!![0].imageUrl
            }
        }
    }

    fun testApi() {
        viewModelScope.launch(Dispatchers.Default) {
            delay(500L)
            val searchedCards = repository.testApi("Water", 5, 0)
            when (searchedCards) {
                is Resource.Success -> {
                    featuredList.value = listOf()
                    val yugiohCards = searchedCards.apiData?.data!!.map {
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
                    featuredList.value += yugiohCards
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
            }
            if (featuredList.value.isNotEmpty()) {
                featuredUrl.value = featuredList.value[0].cardImages!![0].imageUrl
            }
        }
    }

    fun getCurrentShownCard(command: String) {
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

    /* Room functions */

    fun insertCardIntoDeck(yugiohCard: YugiohCard) {
        viewModelScope.launch {
            repository.insertCard(yugiohCard)
        }
    }

    fun getDeckList() {
        getDeckJob?.cancel()
        getDeckJob = repository.getDeckList().map { cards ->
            deckList.value = listOf()
            for (element in cards) {
                deckList.value += listOf(element.id)
            }
        }.launchIn(viewModelScope)
    }


}