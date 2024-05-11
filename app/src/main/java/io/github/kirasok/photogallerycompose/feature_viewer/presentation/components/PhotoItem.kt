package io.github.kirasok.photogallerycompose.feature_viewer.presentation.components

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import io.github.kirasok.photogallerycompose.feature_viewer.domain.model.Photo

@Composable
fun PhotoItem(photo: Photo, height: Dp = 120.dp) {
  AsyncImage(
    model = ImageRequest.Builder(LocalContext.current).data(photo.thumbnail).crossfade(true).build(),
    contentDescription = photo.title,
    modifier = Modifier
      .clip(RectangleShape)
      .height(height),
    contentScale = ContentScale.Crop
  )
}