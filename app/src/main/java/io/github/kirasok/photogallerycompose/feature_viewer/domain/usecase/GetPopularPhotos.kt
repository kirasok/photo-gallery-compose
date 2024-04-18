package io.github.kirasok.photogallerycompose.feature_viewer.domain.usecase

import androidx.paging.PagingData
import io.github.kirasok.photogallerycompose.feature_viewer.domain.model.Photo
import io.github.kirasok.photogallerycompose.feature_viewer.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow

class GetPopularPhotos constructor(private val repository: PhotoRepository) {
  suspend operator fun invoke(): Flow<PagingData<Photo>> = repository.getPopularPhotos()
}