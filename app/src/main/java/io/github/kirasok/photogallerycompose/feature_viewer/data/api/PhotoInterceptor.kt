package io.github.kirasok.photogallerycompose.feature_viewer.data.api

import android.util.Log
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class PhotoInterceptor(private val apiKey: String) : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val originalRequest = chain.request()

    Log.d(null, "Got original request: $originalRequest")

    val newUrl: HttpUrl = originalRequest.url.newBuilder().apply {
      addQueryParameter("api_key", apiKey)
      addQueryParameter("format", "json")
      addQueryParameter("nojsoncallback", "1")
      addQueryParameter("extras", "url_s")
    }.build()

    val newRequest: Request = originalRequest.newBuilder().url(newUrl).build()

    Log.d(null, "Made new request: $newRequest")

    return chain.proceed(newRequest)
  }
}