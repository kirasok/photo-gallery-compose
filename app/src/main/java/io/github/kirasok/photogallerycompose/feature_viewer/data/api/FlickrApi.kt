package io.github.kirasok.photogallerycompose.feature_viewer.data.api

import io.github.kirasok.photogallerycompose.feature_viewer.data.dto.FlickrResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {

  @GET("services/rest?method=flickr.interestingness.getList")
  suspend fun getPopularPhotos(
    @Query("per_page") perPage: Int,
    @Query("page") page: Int,
  ): FlickrResponse

  @GET("services/rest?method=flickr.photos.search&sort=relevance")
  suspend fun searchPhotos(
    @Query("per_page") perPage: Int,
    @Query("page") page: Int,
    @Query("text") query: String,
  ): FlickrResponse

}