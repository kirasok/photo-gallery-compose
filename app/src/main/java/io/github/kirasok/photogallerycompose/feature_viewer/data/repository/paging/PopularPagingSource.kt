package io.github.kirasok.photogallerycompose.feature_viewer.data.repository.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.kirasok.photogallerycompose.feature_viewer.data.source.PhotoRemoteDataSource
import io.github.kirasok.photogallerycompose.feature_viewer.domain.model.Photo
import retrofit2.HttpException
import java.io.IOException

class PopularPagingSource(private val remoteDataSource: PhotoRemoteDataSource) :
  PagingSource<Int, Photo>() {
  override fun getRefreshKey(state: PagingState<Int, Photo>): Int? = state.anchorPosition

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> = try {
    val currentPage = params.key ?: 1
    val photos = remoteDataSource.getPopularPhotos(params.loadSize, currentPage)
    Log.i(null, "Got photos")
    Log.d(null, photos.toString())
    LoadResult.Page(
      data = photos.photosResponse.photos,
      prevKey = if (currentPage == 1) null else currentPage - 1,
      nextKey = if (currentPage != photos.photosResponse.pages) currentPage + 1 else null
    )
  } catch (exception: IOException) {
    Log.e(null, exception.toString())
    LoadResult.Error(exception)
  } catch (exception: HttpException) {
    Log.e(null, exception.toString())
    LoadResult.Error(exception)
  }
}