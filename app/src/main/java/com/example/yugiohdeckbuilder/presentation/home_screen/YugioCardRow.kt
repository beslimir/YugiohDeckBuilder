package com.example.yugiohdeckbuilder.presentation.home_screen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.yugiohdeckbuilder.data.remote.dto.YugiohCard

@Composable
fun YugiohCardRow(
    entries: List<YugiohCard>,
    index: Int,
    context: Context,
    viewModel: HomeScreenViewModel,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 4.dp)
            .clickable {
                //Change the featured card
                viewModel.featuredUrl.value = entries[index].cardImages!![0].imageUrl
                viewModel.currentCardShown.value = index
            }
    ) {
        Row(
            modifier = Modifier.padding(4.dp)
        ) {
            val painter = rememberImagePainter(
                data = entries[index].cardImages!![0].imageUrlSmall
            )
            Image(
                painter = painter,
                contentDescription = entries[index].name,
                modifier = Modifier
                    .height(70.dp)
            )
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