package io.github.kirasok.photogallerycompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.kirasok.photogallerycompose.BuildConfig
import io.github.kirasok.photogallerycompose.feature_viewer.data.api.FlickrApi
import io.github.kirasok.photogallerycompose.feature_viewer.data.api.PhotoInterceptor
import io.github.kirasok.photogallerycompose.feature_viewer.data.repository.PhotoRepositoryImpl
import io.github.kirasok.photogallerycompose.feature_viewer.data.source.PhotoRemoteDataSource
import io.github.kirasok.photogallerycompose.feature_viewer.data.source.PhotoRemoteDataSourceImpl
import io.github.kirasok.photogallerycompose.feature_viewer.domain.repository.PhotoRepository
import io.github.kirasok.photogallerycompose.feature_viewer.domain.usecase.GetPopularPhotos
import io.github.kirasok.photogallerycompose.feature_viewer.domain.usecase.PhotoUseCases
import io.github.kirasok.photogallerycompose.feature_viewer.domain.usecase.SearchPhotos
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Singleton
  @Provides
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl("https://api.flickr.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()

  @Singleton
  @Provides
  fun provideOkHttpClint() =
    OkHttpClient.Builder().addInterceptor(PhotoInterceptor(BuildConfig.API_KEY))
      .build() //TODO: store API_KEY in shared preferences and ask user on login

  @Singleton
  @Provides
  fun provideFlickrApi(retrofit: Retrofit): FlickrApi = retrofit.create(FlickrApi::class.java)

  @Singleton
  @Provides
  fun providePhotoRemoteDataSource(flickrApi: FlickrApi): PhotoRemoteDataSource =
    PhotoRemoteDataSourceImpl(flickrApi)

  @Singleton
  @Provides
  fun providePhotoRepository(remoteDataSource: PhotoRemoteDataSource): PhotoRepository =
    PhotoRepositoryImpl(remoteDataSource)

  @Singleton
  @Provides
  fun providePhotoUseCases(photoRepository: PhotoRepository) = PhotoUseCases(
    GetPopularPhotos(photoRepository),
    SearchPhotos(photoRepository)
  )

}