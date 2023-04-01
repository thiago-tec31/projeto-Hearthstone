package com.example.projecthearthstone.data.network

import com.example.projecthearthstone.data.model.response.FigureCardsResponse
import com.example.projecthearthstone.data.model.response.InfoCardsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.IOException
import kotlin.jvm.Throws

interface CardApi {

    @GET("info") @Throws(IOException::class)
    suspend fun getInfoCards(): Response<InfoCardsResponse>

    @GET("cards/classes/{classes}") @Throws(IOException::class)
    suspend fun getFiguresFilterByClass(@Path("classes") classes: String): Response<List<FigureCardsResponse>>

    @GET("cards/types/{type}") @Throws(IOException::class)
    suspend fun getFiguresFilterByType(@Path("type") type: String): Response<List<FigureCardsResponse>>

    @GET("cards/races/{race}") @Throws(IOException::class)
    suspend fun getFiguresFilterByRace(@Path("race") race: String): Response<List<FigureCardsResponse>>
}