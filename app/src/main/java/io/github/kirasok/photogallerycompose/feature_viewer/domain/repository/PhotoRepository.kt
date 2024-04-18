package io.github.kirasok.photogallerycompose.feature_viewer.domain.repository

import androidx.paging.PagingData
import io.github.kirasok.photogallerycompose.feature_viewer.domain.model.Photo
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
  suspend fun getPopularPhotos(): Flow<PagingData<Photo>>

  suspend fun searchPhotos(query: String): Flow<PagingData<Photo>>
}