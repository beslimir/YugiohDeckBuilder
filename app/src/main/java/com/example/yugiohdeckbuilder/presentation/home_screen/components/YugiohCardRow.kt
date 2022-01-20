package com.example.yugiohdeckbuilder.presentation.home_screen.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.yugiohdeckbuilder.data.remote.dto.YugiohCard
import com.example.yugiohdeckbuilder.presentation.home_screen.HomeScreenViewModel
import com.example.yugiohdeckbuilder.presentation.loadPictureWithGlide

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
            Column(
                modifier = Modifier
                    .height(70.dp)
                    .width(50.dp)
            ) {
                val featuredImage = loadPictureWithGlide(url = entries[index].cardImages!![0].imageUrlSmall, viewModel = viewModel).value
                featuredImage?.let { img ->
                    Image(
                        bitmap = img.asImageBitmap(),
                        contentDescription = null
                    )
                }
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