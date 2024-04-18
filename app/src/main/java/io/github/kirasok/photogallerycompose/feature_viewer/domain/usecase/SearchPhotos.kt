package io.github.kirasok.photogallerycompose.feature_viewer.domain.usecase

import io.github.kirasok.photogallerycompose.feature_viewer.domain.repository.PhotoRepository

class SearchPhotos(private val photoRepository: PhotoRepository) {
  suspend operator fun invoke(query: String) = photoRepository.searchPhotos(query)
}