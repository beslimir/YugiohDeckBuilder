package com.example.yugiohdeckbuilder.presentation.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.yugiohdeckbuilder.R
import com.example.yugiohdeckbuilder.presentation.FeaturedCardSection
import com.example.yugiohdeckbuilder.presentation.home_screen.components.CardList

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {
    val isLoading by remember { viewModel.isLoading }

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            //Top section
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.yugioh_logo),
                    contentDescription = "Yugioh Logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                )
            }

            //Featured Yugioh cards section
            FeaturedCardSection(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .size(40.dp),
                viewModel
            )

            //List of cards
            CardList(
                context = LocalContext.current,
                viewModel = viewModel
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = MaterialTheme.colors.primary)
                }
            }
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