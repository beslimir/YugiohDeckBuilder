package com.example.yugiohdeckbuilder.presentation.home_screen.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.yugiohdeckbuilder.presentation.home_screen.HomeScreenViewModel

@Composable
fun CardList(
    context: Context,
    viewModel: HomeScreenViewModel,
) {
    val featuredList by remember { viewModel.featuredList }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.background(Color.LightGray)
        ) {
            items(featuredList.size) {
                YugiohCardRow(
                    entries = featuredList,
                    index = it,
                    context = context,
                    viewModel = viewModel
                )
            }
        }
    }

}