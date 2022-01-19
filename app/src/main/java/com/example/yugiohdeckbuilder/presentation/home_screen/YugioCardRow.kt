package com.example.yugiohdeckbuilder.presentation.home_screen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 4.dp)
            .clickable {
                //Change the featured card
                viewModel.featuredUrl.value = entries[index].cardImages!![0].imageUrl
                viewModel.currentCardShown.value = index
            }
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
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = entries[index].desc,
                    style = TextStyle(
                        fontSize = 10.sp
                    )
                )
            }
        }
    }
    Divider(
        thickness = 1.dp,
        color = Color.LightGray
    )

}