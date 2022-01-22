package com.example.yugiohdeckbuilder.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.yugiohdeckbuilder.presentation.home_screen.HomeScreenViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun FeaturedCardSection(
    modifier: Modifier,
    viewModel: HomeScreenViewModel,
) {
    val featuredList by remember { viewModel.featuredList }
    val featuredUrl by remember { viewModel.featuredUrl }

    Box(
        contentAlignment = Alignment.Center
    ) {
        if (featuredList.isNotEmpty()) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = modifier
                            .clickable {
                                viewModel.getCurrentShownCard("left")
                            },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Arrow back",
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(200.dp)
                            .weight(2f),
                        contentAlignment = Alignment.Center
                    ) {
                        GlideImage(
                            imageModel = featuredUrl,
                            modifier = Modifier
                                .clickable {

                                },
                            loading = {
                                CircularProgressIndicator()
                            },
                            contentScale = ContentScale.Fit
                        )
                    }

                    Column(
                        modifier = modifier
                            .clickable {
                                viewModel.getCurrentShownCard("right")
                            },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Arrow forward",
                        )
                    }
                }
            }
        }
    }

}