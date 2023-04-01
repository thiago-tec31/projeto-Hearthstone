package com.example.projecthearthstone.data.repository

import com.example.projecthearthstone.core.model.ApiError
import com.example.projecthearthstone.core.model.Resource
import com.example.projecthearthstone.data.mapper.FiguresCardsMapper
import com.example.projecthearthstone.data.model.response.FigureCardsResponse
import com.example.projecthearthstone.data.network.CardApi
import com.example.projecthearthstone.domain.model.CardType
import com.example.projecthearthstone.domain.model.FigureCards
import com.example.projecthearthstone.domain.repository.InsiderHearthstoneRepository
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class InsiderHearthstoneRepositoryImpl(
    private val infoApi: CardApi,
    private val figuresCardsMapper: FiguresCardsMapper
): InsiderHearthstoneRepository {

    override suspend fun getFiguresAll(filter: CardType, cardName: String): Resource<List<FigureCards>> {
        return try {

            val response = filterFigures(filter, cardName)

            if (response.isSuccessful && response.body() != null) {
                Resource.Success(figuresCardsMapper.map(response.body()!!))
            } else {
                var message = response.code().toString()

                if(response.body() != null){
                    message += "- ${response.body()}"
                }

                if(response.message().isNotBlank()){
                    message += "- ${response.message()}"
                }

                Resource.Fail(ApiError.FAIL, message)
            }

        } catch (e: UnknownHostException) {
            Resource.Fail(ApiError.NO_CONNECTION, e.message ?: e.toString())
        } catch (e: SocketTimeoutException) {
            Resource.Fail(ApiError.TIMEOUT, e.message ?: e.toString())
        } catch (e: Exception) {
            Resource.Fail(ApiError.EXCEPTION, e.message ?: e.toString())
        }
    }

    private suspend fun filterFigures(filter: CardType, cardName: String): Response<List<FigureCardsResponse>> {
        return when(filter) {
            CardType.CLASSES -> infoApi.getFiguresFilterByClass(cardName)
            CardType.TYPES -> infoApi.getFiguresFilterByType(cardName)
            CardType.RACES -> infoApi.getFiguresFilterByRace(cardName)
        }
    }
}