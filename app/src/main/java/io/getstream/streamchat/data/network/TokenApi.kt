package io.getstream.streamchat.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface TokenApi {

  @GET("/token")
  suspend fun getUserToken(
    @Query("user_id") userId: String
  ): TokenResponse

}
