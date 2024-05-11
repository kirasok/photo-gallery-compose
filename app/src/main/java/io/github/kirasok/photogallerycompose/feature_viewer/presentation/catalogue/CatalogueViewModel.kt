package io.github.kirasok.photogallerycompose.feature_viewer.presentation.catalogue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.kirasok.photogallerycompose.feature_viewer.domain.model.Photo
import io.github.kirasok.photogallerycompose.feature_viewer.domain.usecase.PhotoUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogueViewModel @Inject constructor(private val useCases: PhotoUseCases) : ViewModel() {

  private val _photoState: MutableStateFlow<PagingData<Photo>> =
    MutableStateFlow(value = PagingData.empty())
  val photoState: StateFlow<PagingData<Photo>> = _photoState

  init {
    onEvent(CatalogueEvent.GetPopular)
  }

  fun onEvent(event: CatalogueEvent) {
    viewModelScope.launch {
      when (event) {
        CatalogueEvent.GetPopular -> useCases.getPopularPhotos().distinctUntilChanged()
          .cachedIn(viewModelScope).collect { _photoState.value = it }

        is CatalogueEvent.Search -> {
          useCases.searchPhotos(event.query).distinctUntilChanged().cachedIn(viewModelScope)
            .collect { _photoState.value = it }
        }
      }
    }
  }


  sealed class CatalogueEvent {
    data object GetPopular : CatalogueEvent()

    data class Search(val query: String) : CatalogueEvent()
  }
}