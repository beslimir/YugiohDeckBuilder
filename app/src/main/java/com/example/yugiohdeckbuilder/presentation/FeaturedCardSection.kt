package com.example.yugiohdeckbuilder.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yugiohdeckbuilder.presentation.home_screen.HomeScreenViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun FeaturedCardSection(
    modifier: Modifier,
    viewModel: HomeScreenViewModel,
) {
    val featuredList by remember { viewModel.featuredList }
    val featuredUrl by remember { viewModel.featuredUrl }
    val loadError by remember { viewModel.loadError }

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
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp,
                            end = 8.dp,
                            top = 4.dp,
                            bottom = 4.dp)
                        .border(1.dp, Color.LightGray)
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                    ) {
                        Text(text = "Type",
                            style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.SemiBold))
                        Text(text = featuredList[viewModel.currentCardShown.value].type,
                            style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold))
                    }
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                    ) {
                        Text(text = "Race",
                            style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.SemiBold))
                        Text(text = featuredList[viewModel.currentCardShown.value].race,
                            style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold))
                    }
                    if (featuredList[viewModel.currentCardShown.value].type.contains("Monster")) {
                        Column(
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                        ) {
                            Text(text = "Attribute",
                                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.SemiBold))
                            Text(text = featuredList[viewModel.currentCardShown.value].attribute!!.lowercase().replaceFirstChar {
                                it.uppercase()
                            },
                                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold))
                        }
                        Column(
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                        ) {
                            Text(text = "ATK",
                                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.SemiBold))
                            Text(text = featuredList[viewModel.currentCardShown.value].atk.toString(),
                                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold))
                        }
                        Column(
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                        ) {
                            Text(text = "DEF",
                                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.SemiBold))
                            Text(text = featuredList[viewModel.currentCardShown.value].def.toString(),
                                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold))
                        }
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = loadError)
            }
        }
    }

}