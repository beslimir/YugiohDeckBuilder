package com.example.yugiohdeckbuilder.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.yugiohdeckbuilder.presentation.home_screen.HomeScreenViewModel
import com.example.yugiohdeckbuilder.presentation.home_screen.YugiohCardRow

@Composable
fun FeaturedCardSection(
    modifier: Modifier,
    context: Context,
    viewModel: HomeScreenViewModel,
) {
    val currentCard by remember { viewModel.currentCardShown }
    val featuredList by remember { viewModel.featuredList }

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
                    val defaultUrl = featuredList[1].cardImages!![0].imageUrl
                    val rightCard = featuredList[2].cardImages!![0].imageUrl
                    val leftCard = featuredList[0].cardImages!![0].imageUrl

                    val imageDefault = loadPictureWithGlide(url = defaultUrl).value
                    val imageLeft = loadPictureWithGlide(url = leftCard).value
                    val imageRight = loadPictureWithGlide(url = rightCard).value

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
                            .weight(2f)
                    ) {
                        if (currentCard == 1) {
                            imageLeft?.let { img ->
                                Image(
                                    bitmap = img.asImageBitmap(),
                                    contentDescription = featuredList[1].name,
                                    modifier = Modifier
                                        .size(200.dp)
                                        .clickable {

                                        }
                                )
                            }
                        } else if (currentCard == 2) {
                            imageDefault?.let { img ->
                                Image(
                                    bitmap = img.asImageBitmap(),
                                    contentDescription = featuredList[0].name,
                                    modifier = Modifier
                                        .size(200.dp)
                                        .clickable {

                                        }
                                )
                            }
                        } else if (currentCard == 3) {
                            imageRight?.let { img ->
                                Image(
                                    bitmap = img.asImageBitmap(),
                                    contentDescription = featuredList[2].name,
                                    modifier = Modifier
                                        .size(200.dp)
                                        .clickable {

                                        }
                                )
                            }
                        }
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    LazyColumn(contentPadding = PaddingValues(16.dp)) {
                        items(featuredList.size) {
                            YugiohCardRow(
                                entries = featuredList,
                                index = it
                            )
                        }
                    }
                }
            }
        }
    }

}

@SuppressLint("UnrememberedMutableState")
@Composable
fun loadPictureWithGlide(
    url: String,
): MutableState<Bitmap?> {

    val bitmapState: MutableState<Bitmap?> = mutableStateOf(null)

    Glide.with(LocalContext.current)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })

    return bitmapState
}