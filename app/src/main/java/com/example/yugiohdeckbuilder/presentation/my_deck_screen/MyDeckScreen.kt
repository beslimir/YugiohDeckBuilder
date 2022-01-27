package com.example.yugiohdeckbuilder.presentation.my_deck_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun MyDeckScreen() {
    Column() {
        Text(text = "Hello MyDeck enterers xD")
    }
}