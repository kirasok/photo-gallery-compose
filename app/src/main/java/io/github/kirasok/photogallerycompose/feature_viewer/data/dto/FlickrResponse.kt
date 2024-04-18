package io.github.kirasok.photogallerycompose.feature_viewer.data.dto

import com.google.gson.annotations.SerializedName

data class FlickrResponse(
  @SerializedName("photos") val photosResponse: PhotosResponse,
)
