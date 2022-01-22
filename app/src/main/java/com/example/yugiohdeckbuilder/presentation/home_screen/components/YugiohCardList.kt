package com.example.yugiohdeckbuilder.presentation.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
    viewModel: HomeScreenViewModel,
) {
    val featuredList by remember { viewModel.featuredList }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.background(Color.LightGray).fillMaxSize()
        ) {
            items(featuredList.size) {
                YugiohCardRow(
                    entries = featuredList,
                    index = it,
                    viewModel = viewModel
                )
            }
        }
    }

}