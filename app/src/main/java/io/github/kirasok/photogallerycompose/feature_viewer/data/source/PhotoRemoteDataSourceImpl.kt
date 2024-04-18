package io.github.kirasok.photogallerycompose.feature_viewer.data.source

import io.github.kirasok.photogallerycompose.feature_viewer.data.api.FlickrApi
import io.github.kirasok.photogallerycompose.feature_viewer.data.dto.FlickrResponse

class PhotoRemoteDataSourceImpl(private val api: FlickrApi) :
  PhotoRemoteDataSource {
  override suspend fun getPopularPhotos(perPage: Int, page: Int): FlickrResponse =
    api.getPopularPhotos(perPage, page)

  override suspend fun searchPhotos(query: String, page: Int, perPage: Int) =
    api.searchPhotos(perPage, page, query)
}