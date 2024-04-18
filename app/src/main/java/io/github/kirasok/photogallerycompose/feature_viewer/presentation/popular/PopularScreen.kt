package io.github.kirasok.photogallerycompose.feature_viewer.presentation.popular

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import io.github.kirasok.photogallerycompose.feature_viewer.domain.model.Photo
import io.github.kirasok.photogallerycompose.feature_viewer.presentation.components.PhotoItem

@Composable
fun PopularScreen(viewModel: PopularViewModel = hiltViewModel()) {
  val photoPagingItems: LazyPagingItems<Photo> = viewModel.photoState.collectAsLazyPagingItems()

  Scaffold { padding ->
    LazyVerticalGrid(
      modifier = Modifier.padding(padding),
      columns = GridCells.Adaptive(minSize = 128.dp),
      contentPadding = PaddingValues(4.dp),
      verticalArrangement = Arrangement.Center,
      horizontalArrangement = Arrangement.Center
    ) {
      items(photoPagingItems.itemCount) { index ->
        PhotoItem(photo = photoPagingItems[index]!!)
      }
    }
  }
}