package io.github.kirasok.photogallerycompose.feature_viewer.domain.model

import com.google.gson.annotations.SerializedName

data class Photo(
  val id: String,
  val isFamily: Int,
  val isFriend: Int,
  val isPublic: Int,
  val owner: String,
  val secret: String,
  val server: String,
  val title: String,
  @SerializedName("url_s") val url: String,
) {
}