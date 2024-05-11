package io.github.kirasok.photogallerycompose.feature_viewer.data.repository.paging

import io.github.kirasok.photogallerycompose.feature_viewer.data.dto.FlickrResponse
import io.github.kirasok.photogallerycompose.feature_viewer.data.source.PhotoRemoteDataSource

class PopularPagingSource(private val remoteDataSource: PhotoRemoteDataSource) :
  PhotoPagingSource() {
  override suspend fun request(loadSize: Int, currentPage: Int): FlickrResponse =
    remoteDataSource.getPopularPhotos(loadSize, currentPage)
}