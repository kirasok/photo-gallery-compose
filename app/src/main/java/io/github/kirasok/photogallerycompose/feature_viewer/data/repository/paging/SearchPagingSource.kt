package io.github.kirasok.photogallerycompose.feature_viewer.data.repository.paging

import io.github.kirasok.photogallerycompose.feature_viewer.data.dto.FlickrResponse
import io.github.kirasok.photogallerycompose.feature_viewer.data.source.PhotoRemoteDataSource

class SearchPagingSource(
  private val remoteDataSource: PhotoRemoteDataSource,
  private val query: String,
) :
  PhotoPagingSource() {
  override suspend fun request(loadSize: Int, currentPage: Int): FlickrResponse =
    remoteDataSource.searchPhotos(query, currentPage, loadSize)
}
