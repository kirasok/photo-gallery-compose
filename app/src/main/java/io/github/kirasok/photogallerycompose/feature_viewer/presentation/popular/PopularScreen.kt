package io.github.kirasok.photogallerycompose.feature_viewer.presentation.popular

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import io.github.kirasok.photogallerycompose.feature_viewer.domain.model.Photo
import io.github.kirasok.photogallerycompose.feature_viewer.presentation.components.PhotoGrid

@Composable
fun PopularScreen(viewModel: PopularViewModel = hiltViewModel()) {
  val photoPagingItems: LazyPagingItems<Photo> = viewModel.photoState.collectAsLazyPagingItems()

  Scaffold { padding ->
    PhotoGrid(modifier = Modifier.padding(padding), photoPagingItems = photoPagingItems)
  }
}