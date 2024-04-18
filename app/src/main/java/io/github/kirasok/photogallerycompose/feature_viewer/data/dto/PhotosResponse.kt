package io.github.kirasok.photogallerycompose.feature_viewer.data.dto

import com.google.gson.annotations.SerializedName
import io.github.kirasok.photogallerycompose.feature_viewer.domain.model.Photo

data class PhotosResponse(
  @SerializedName("photo") val photos: List<Photo>,
  val page: Int,
  val pages: Int,
)