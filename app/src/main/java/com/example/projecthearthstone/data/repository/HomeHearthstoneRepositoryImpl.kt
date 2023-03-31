package com.example.projecthearthstone.data.repository

import com.example.projecthearthstone.core.model.ApiError
import com.example.projecthearthstone.core.model.Resource
import com.example.projecthearthstone.data.mapper.InfoCardsMapper
import com.example.projecthearthstone.data.network.CardApi
import com.example.projecthearthstone.domain.model.InfoCards
import com.example.projecthearthstone.domain.repository.HomeHearthstoneRepository
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class HomeHearthstoneRepositoryImpl(
    private val infoApi: CardApi,
    private val infoCardsMapper: InfoCardsMapper
): HomeHearthstoneRepository {

    override suspend fun getInfoCards(): Resource<InfoCards> {
        return try {
            val response = infoApi.getInfoCards()

            if (response.isSuccessful && response.body() != null) {
                Resource.Success(infoCardsMapper.map(response.body()!!))
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
            Resource.Fail(ApiError.TIME_OUT, e.message ?: e.toString())
        } catch (e: Exception) {
            Resource.Fail(ApiError.EXCEPTION, e.message ?: e.toString())
        }
    }
}