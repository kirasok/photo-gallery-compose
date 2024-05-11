package io.github.kirasok.photogallerycompose.feature_viewer.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import io.github.kirasok.photogallerycompose.feature_viewer.domain.model.Photo

@Composable
fun PhotoGrid(
  modifier: Modifier = Modifier,
  minSize: Dp = 128.dp,
  photoPagingItems: LazyPagingItems<Photo>,
) {
  LazyVerticalGrid(
    modifier = modifier,
    columns = GridCells.Adaptive(minSize = minSize),
    verticalArrangement = Arrangement.Center,
    horizontalArrangement = Arrangement.Center
  ) {
    items(photoPagingItems.itemCount) { index ->
      PhotoItem(photo = photoPagingItems[index]!!)
    }
  }
}

