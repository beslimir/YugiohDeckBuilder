package com.example.yugiohdeckbuilder.presentation.home_screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.yugiohdeckbuilder.data.remote.dto.YugiohCard

@Composable
fun YugiohCardRow(
    entries: List<YugiohCard>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = entries[0].name!!)
    }

}