package com.example.yugiohdeckbuilder.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.yugiohdeckbuilder.presentation.home_screen.HomeScreenViewModel

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
            .height(300.dp)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val defaultUrl = "https://storage.googleapis.com/ygoprodeck.com/pics/77585513.jpg"
                val rightCard = "https://storage.googleapis.com/ygoprodeck.com/pics/70781052.jpg"
                val leftCard = "https://storage.googleapis.com/ygoprodeck.com/pics/40640057.jpg"

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
                                contentDescription = "Kuriboh",
                                modifier = Modifier
                                    .size(200.dp)
                                    .clickable {
                                        Toast
                                            .makeText(context, "Kuriboh", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                            )
                        }
                    }
                    else if (currentCard == 2) {
                        imageDefault?.let { img ->
                            Image(
                                bitmap = img.asImageBitmap(),
                                contentDescription = "Jinzo",
                                modifier = Modifier
                                    .size(200.dp)
                                    .clickable {
                                        Toast
                                            .makeText(context, "Jinzo", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                            )
                        }
                    }
                    else if (currentCard == 3) {
                        imageRight?.let { img ->
                            Image(
                                bitmap = img.asImageBitmap(),
                                contentDescription = "Summoned Skull",
                                modifier = Modifier
                                    .size(200.dp)
                                    .clickable {
                                        Toast
                                            .makeText(context, "Summoned Skull", Toast.LENGTH_SHORT)
                                            .show()
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