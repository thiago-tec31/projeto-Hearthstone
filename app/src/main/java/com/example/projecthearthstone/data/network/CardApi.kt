package com.example.projecthearthstone.data.network

import com.example.projecthearthstone.data.model.request.InfoCardsResponse
import retrofit2.Response
import retrofit2.http.GET

interface CardApi {

    @GET("info")
    suspend fun getInfoCards(): Response<InfoCardsResponse>
}