package com.example.yugiohdeckbuilder.presentation

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.yugiohdeckbuilder.presentation.home_screen.HomeScreenViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun FeaturedCardSection(
    modifier: Modifier,
    viewModel: HomeScreenViewModel,
) {
    val featuredList by remember { viewModel.featuredList }
    val featuredUrl by remember { viewModel.featuredUrl }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        if (featuredList.isNotEmpty()) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.height(300.dp)
                ) {
                    Column(
                        modifier = modifier
                            .clickable {
                                viewModel.getCurrentShownCard("left")
                            },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Arrow back",
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(200.dp)
                            .weight(2f),
                        contentAlignment = Alignment.Center
                    ) {
                        GlideImage(
                            imageModel = featuredUrl,
                            modifier = Modifier
                                .clickable {

                                },
                            loading = {
                                CircularProgressIndicator()
                            },
                            contentScale = ContentScale.Fit
                        )
                    }

                    Column(
                        modifier = modifier
                            .clickable {
                                viewModel.getCurrentShownCard("right")
                            },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Arrow forward",
                        )
                    }
                }
            }
        }
    }

}