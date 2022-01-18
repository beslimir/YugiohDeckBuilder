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
                var url by remember {
                    mutableStateOf(defaultUrl)
                }

                loadPictureWithGlide(url = rightCard)
                loadPictureWithGlide(url = leftCard)
                val image = loadPictureWithGlide(url = url).value

                Column(
                    modifier = modifier
                        .clickable {
                            viewModel.getCurrentShownCard("left")

                            url = if (currentCard == 2) {
                                defaultUrl
                            } else {
                                leftCard
                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow back",
                    )
                }

                Column(
                    modifier = Modifier
                        .size(200.dp)
                        .weight(2f)
                ) {
                    image?.let { img ->
                        Image(
                            bitmap = img.asImageBitmap(),
                            contentDescription = "Jinzo",
                            modifier = Modifier
                                .size(200.dp)
                                .clickable {
                                    Toast
                                        .makeText(context, "${featuredList.size}", Toast.LENGTH_SHORT)
                                        .show()
                                }
                        )
                    }

                }

                Column(
                    modifier = modifier
                        .clickable {
                            viewModel.getCurrentShownCard("right")

                            url = if (currentCard == 2) {
                                defaultUrl
                            } else {
                                rightCard
                            }
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