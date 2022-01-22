package com.example.yugiohdeckbuilder.presentation.home_screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yugiohdeckbuilder.data.remote.dto.YugiohCard
import com.example.yugiohdeckbuilder.presentation.home_screen.HomeScreenViewModel
import com.example.yugiohdeckbuilder.presentation.ui.theme.Shapes
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun YugiohCardRow(
    entries: List<YugiohCard>,
    index: Int,
    viewModel: HomeScreenViewModel,
) {

    val indexList by remember { viewModel.indexList }
    var getColor = if (indexList.contains(index)) {
        Color.Red
    } else {
        Color.White
    }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 4.dp)
            .border(1.dp, getColor, shape = Shapes.small)
            .clickable {
                //Change the featured card
                viewModel.featuredUrl.value = entries[index].cardImages!![0].imageUrl
                viewModel.currentCardShown.value = index

                viewModel.indexList.value += index
                getColor = if (indexList.contains(index)) {
                    Color.Red
                } else {
                    Color.White
                }
            }
    ) {
        Row(
            modifier = Modifier.padding(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .height(70.dp)
                    .width(50.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GlideImage(
                    imageModel = entries[index].cardImages!![0].imageUrlSmall,
                    loading = {
                        CircularProgressIndicator()
                    }
                )
            }

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(
                    text = entries[index].name,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Row {
                    Text(
                        text = entries[index].type,
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = entries[index].race,
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    if (entries[index].type.contains("Monster")) {
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(
                            text = "ATK: ${entries[index].atk}",
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "DEF: ${entries[index].def}",
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = entries[index].desc,
                        style = TextStyle(
                            fontSize = 10.sp
                        ),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}