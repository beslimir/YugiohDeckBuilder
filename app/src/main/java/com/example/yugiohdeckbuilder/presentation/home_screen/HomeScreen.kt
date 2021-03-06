package com.example.yugiohdeckbuilder.presentation.home_screen

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.yugiohdeckbuilder.R
import com.example.yugiohdeckbuilder.presentation.destinations.MyDeckScreenDestination
import com.example.yugiohdeckbuilder.presentation.home_screen.components.FeaturedCardSection
import com.example.yugiohdeckbuilder.presentation.home_screen.components.CardList
import com.example.yugiohdeckbuilder.presentation.home_screen.components.SearchBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Destination(start = true)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {

    val isSearchbarVisible by remember { viewModel.isSearchbarVisible }
    var job: Job? = null

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            //Top section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color.LightGray),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(1f)
                ) {
                    AnimatedVisibility(
                        visible = isSearchbarVisible,
                        enter = fadeIn() + slideInHorizontally(),
                        exit = fadeOut() + slideOutHorizontally()
                    ) {
                        SearchBar(viewModel) {
                            job?.cancel()
                            job = MainScope().launch {
                                delay(750L)
                                viewModel.getYugiohCardsByName(it)
                            }
                        }
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Dashboard",
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier.padding(start = 12.dp)
                        )
                        IconButton(
                            onClick = {
                                viewModel.isSearchbarVisible.value = true
                                viewModel.searchValue.value = "Search..."
                                viewModel.searchBarHintDeleted.value = false
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null
                            )
                        }
                    }
                }
                Row {
                    IconButton(
                        onClick = {
                            navigator.navigate(
                                MyDeckScreenDestination()
                            )
                        }
                    ) {
                        Icon(
                            painterResource(id = R.drawable.ic_deck),
                            contentDescription = "My deck"
                        )
                    }
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = null
                        )
                    }
                }
            }

            //Featured Yugioh cards section
            FeaturedCardSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .weight(1f),
                viewModel = viewModel
            )

            //List of cards
            CardList(
                viewModel = viewModel
            )
        }


//        Column(modifier = Modifier.fillMaxSize()) {
//            when (yugiohCardInfo) {
//                is Resource.Success -> {
//                    Log.d("aa", "Success: ${yugiohCardInfo.apiData!!.data[0].name}")
//                }
//                is Resource.Error -> {
//                    when (yugiohCardInfo.errorMessage) {
//                        is ErrorEntity.Network -> {
//                            Log.d("aa", "Error: Network failure")
//                        }
//                        is ErrorEntity.NotFound -> {
//                            Log.d("aa", "Error: Http not found")
//                        }
//                        is ErrorEntity.Unknown -> {
//                            Log.d("aa", "Error: Unknown card")
//                        }
//                        else -> {
//                            Log.d("aa", "Error: Unexpected error")
//                        }
//                    }
//                }
//                else -> {
//
//                }
//            }
//        }

    }

}