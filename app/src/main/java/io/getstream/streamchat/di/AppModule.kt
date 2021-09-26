package io.getstream.streamchat.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.getstream.streamchat.data.network.RemoteDataSource
import io.getstream.streamchat.data.network.TokenApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Singleton
  @Provides
  fun provideTokenApi(
    remoteDataSource: RemoteDataSource
  ): TokenApi {
    return remoteDataSource.buildApi(TokenApi::class.java)
  }
}
