package com.example.yugiohdeckbuilder.presentation.home_screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.yugiohdeckbuilder.data.remote.dto.YugiohList
import com.example.yugiohdeckbuilder.domain.error_handler.ErrorEntity
import com.example.yugiohdeckbuilder.util.Resource

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val yugiohCardInfo = produceState<Resource<YugiohList>>(initialValue = Resource.Loading()) {
        value = viewModel.getYugiohCardByName()
    }.value

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            when (yugiohCardInfo) {
                is Resource.Success -> {
                    Log.d("aa", "Success: ${yugiohCardInfo.apiData!!.data[0].name}")
                }
                is Resource.Error -> {
                    when (yugiohCardInfo.errorMessage) {
                        is ErrorEntity.Network -> {
                            Log.d("aa", "Error: Network failure")
                        }
                        is ErrorEntity.NotFound -> {
                            Log.d("aa", "Error: Http not found")
                        }
                        is ErrorEntity.Unknown -> {
                            Log.d("aa", "Error: Unknown card")
                        }
                        else -> {
                            Log.d("aa", "Error: Unexpected error")
                        }
                    }
                }
                else -> {

                }
            }
        }

    }

}