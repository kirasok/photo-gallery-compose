package io.github.kirasok.photogallerycompose.feature_viewer.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import io.github.kirasok.photogallerycompose.feature_viewer.data.repository.paging.PopularPagingSource
import io.github.kirasok.photogallerycompose.feature_viewer.data.repository.paging.SearchPagingSource
import io.github.kirasok.photogallerycompose.feature_viewer.data.source.PhotoRemoteDataSource
import io.github.kirasok.photogallerycompose.feature_viewer.domain.model.Photo
import io.github.kirasok.photogallerycompose.feature_viewer.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow

class PhotoRepositoryImpl(private val remoteDataSource: PhotoRemoteDataSource) : PhotoRepository {
  override suspend fun getPopularPhotos(): Flow<PagingData<Photo>> = Pager(
    config = PagingConfig(42),
    pagingSourceFactory = {
      PopularPagingSource(remoteDataSource)
    }
  ).flow

  override suspend fun searchPhotos(query: String): Flow<PagingData<Photo>> = Pager(
    config = PagingConfig(42),
    pagingSourceFactory = {
      SearchPagingSource(remoteDataSource, query)
    }
  ).flow
}