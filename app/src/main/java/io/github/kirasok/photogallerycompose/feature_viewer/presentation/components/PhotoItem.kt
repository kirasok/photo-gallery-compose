package io.github.kirasok.photogallerycompose.feature_viewer.presentation.components

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.github.kirasok.photogallerycompose.feature_viewer.domain.model.Photo

@Composable
fun PhotoItem(photo: Photo) {
  AsyncImage(
    model = photo.url,
    contentDescription = photo.title,
    modifier = Modifier.clip(RectangleShape).height(120.dp),
    contentScale = ContentScale.Crop
  )
}