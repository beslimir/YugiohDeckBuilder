package com.example.yugiohdeckbuilder.presentation.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.yugiohdeckbuilder.presentation.home_screen.HomeScreenViewModel

@Composable
fun SearchBar(
    viewModel: HomeScreenViewModel,
    onSearch: (String) -> Unit = {}
) {
    var value by remember { viewModel.searchValue }
    val isHintDeleted by remember { viewModel.searchBarHintDeleted }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .shadow(4.dp, RoundedCornerShape(26.dp))
            .clip(AbsoluteRoundedCornerShape(20.dp))
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = value,
            onValueChange = {
                value = it
                onSearch(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 12.dp)
                .onFocusEvent { focusState ->
                    if (focusState.hasFocus && !isHintDeleted) {
                        viewModel.searchValue.value = ""
                        viewModel.searchBarHintDeleted.value = true
                    }
                },
            decorationBox = { innerTextField ->
                innerTextField()
            }
        )
        IconButton(
            onClick = {
                viewModel.isSearchbarVisible.value = false
            }
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        }
    }
}