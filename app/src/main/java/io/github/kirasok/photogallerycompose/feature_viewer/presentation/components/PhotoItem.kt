package io.github.kirasok.photogallerycompose.feature_viewer.presentation.components

import androidx.compose.runtime.Composable
import coil.compose.AsyncImage
import io.github.kirasok.photogallerycompose.feature_viewer.domain.model.Photo

@Composable
fun PhotoItem(photo: Photo) {
  AsyncImage(model = photo.url, contentDescription = photo.title)
}