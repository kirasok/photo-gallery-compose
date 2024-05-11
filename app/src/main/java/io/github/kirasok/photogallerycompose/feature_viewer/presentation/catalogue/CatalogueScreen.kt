package io.github.kirasok.photogallerycompose.feature_viewer.presentation.catalogue

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import io.github.kirasok.photogallerycompose.feature_viewer.domain.model.Photo
import io.github.kirasok.photogallerycompose.feature_viewer.presentation.components.PhotoGrid
import io.github.kirasok.photogallerycompose.feature_viewer.presentation.components.PhotoSearchBar

@Composable
fun CatalogueScreen(viewModel: CatalogueViewModel = hiltViewModel()) {
  val photoPagingItems: LazyPagingItems<Photo> = viewModel.photoState.collectAsLazyPagingItems()

  var searchActive by remember { mutableStateOf(false) }
  var query by remember { mutableStateOf("") }

  Scaffold(
    topBar = {
      PhotoSearchBar(
        query = query,
        onQueryChange = { query = it },
        onSearch = { viewModel.onEvent(CatalogueViewModel.CatalogueEvent.Search(query)) },
        active = searchActive,
        onActiveChange = {
          searchActive = it
          if (!searchActive) {
            query = ""
            viewModel.onEvent(CatalogueViewModel.CatalogueEvent.GetPopular)
          }
        }
      )
    }
  ) { padding ->
    PhotoGrid(modifier = Modifier.padding(padding), photoPagingItems = photoPagingItems)
  }
}