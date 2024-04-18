package io.github.kirasok.photogallerycompose.feature_viewer.data.source

import io.github.kirasok.photogallerycompose.feature_viewer.data.dto.FlickrResponse

interface PhotoRemoteDataSource {

  suspend fun getPopularPhotos(
    perPage: Int,
    page: Int,
  ): FlickrResponse

  suspend fun searchPhotos(
    query: String,
    page: Int,
    perPage: Int,
  ): FlickrResponse
}