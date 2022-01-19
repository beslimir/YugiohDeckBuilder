package com.example.yugiohdeckbuilder.presentation

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
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
                    val featuredImage =
                        loadPictureWithGlide(url = featuredUrl, viewModel = viewModel).value

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
                        if (viewModel.isFeaturedCardLoading.value) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colors.primary,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                        featuredImage?.let { img ->
                            Image(
                                bitmap = img.asImageBitmap(),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(200.dp)
                                    .clickable {

                                    }
                            )
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

}

@SuppressLint("UnrememberedMutableState")
@Composable
fun loadPictureWithGlide(
    url: String,
    viewModel: HomeScreenViewModel,
): MutableState<Bitmap?> {

    val bitmapState: MutableState<Bitmap?> = mutableStateOf(null)

    Glide.with(LocalContext.current)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource
                viewModel.isFeaturedCardLoading.value = false
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })

    return bitmapState
}