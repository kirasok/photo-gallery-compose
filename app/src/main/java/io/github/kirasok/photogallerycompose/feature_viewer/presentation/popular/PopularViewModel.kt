package io.github.kirasok.photogallerycompose.feature_viewer.presentation.popular

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
class PopularViewModel @Inject constructor(private val useCases: PhotoUseCases) : ViewModel() {

  private val _photoState: MutableStateFlow<PagingData<Photo>> =
    MutableStateFlow(value = PagingData.empty())
  val photoState: StateFlow<PagingData<Photo>> = _photoState

  init {
    onEvent(HomeEvent.GetHome)
  }

  fun onEvent(event: HomeEvent) {
    viewModelScope.launch {
      when (event) {
        HomeEvent.GetHome -> useCases.getPopularPhotos().distinctUntilChanged()
          .cachedIn(viewModelScope).collect { _photoState.value = it }
      }
    }
  }

}

sealed class HomeEvent {
  data object GetHome : HomeEvent()
}