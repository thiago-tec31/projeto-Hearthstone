package com.example.projecthearthstone.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.projecthearthstone.core.model.ApiError
import com.example.projecthearthstone.core.model.Resource
import com.example.projecthearthstone.data.mapper.InfoCardsMapper
import com.example.projecthearthstone.data.model.response.InfoCardsResponse
import com.example.projecthearthstone.data.network.CardApi
import com.example.projecthearthstone.domain.model.InfoCards
import com.example.projecthearthstone.presentation.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import java.io.InvalidObjectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeHearthstoneRepositoryImplTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var cardApi: CardApi

    @Mock
    private lateinit var infoCardsMapper: InfoCardsMapper

    private lateinit var homeHearthstoneRepositoryImpl: HomeHearthstoneRepositoryImpl


    @Before
    fun setUp() {
        homeHearthstoneRepositoryImpl = HomeHearthstoneRepositoryImpl(
            cardApi,
            infoCardsMapper
        )
    }

    @Test
    fun testGetInfoCardsSuccess() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        try {
            // Arrange
            val infoCardsResponse = InfoCardsResponse(
                arrayListOf(Constants.classMage),
                arrayListOf(Constants.typeDruid),
                arrayListOf(Constants.raceOrc)
            )

            val success = Response.success(infoCardsResponse)

            val infoCards = InfoCards(
                arrayListOf(Constants.classMage),
                arrayListOf(Constants.typeDruid),
                arrayListOf(Constants.raceOrc)
            )

            `when`(cardApi.getInfoCards()).thenReturn(success)
            `when`(infoCardsMapper.map(infoCardsResponse)).thenReturn(infoCards)

            // Act
            val result = homeHearthstoneRepositoryImpl.getInfoCards()

            // Assert
            verify(cardApi, times(1)).getInfoCards()

            with(result) {
                Assert.assertEquals(Constants.classMage, (this as Resource.Success).data.classes[0])
                Assert.assertEquals(Constants.typeDruid, this.data.types[0])
                Assert.assertEquals(Constants.raceOrc, this.data.races[0])
            }

        } finally {
            Dispatchers.resetMain()
        }
    }

    @Test
    fun testGetInfoCardsFail() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        try {
            // Arrange
            val error: Response<InfoCardsResponse> = Response.error(
                404,
                "{key:Error 404 Not Found}"
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )

            `when`(cardApi.getInfoCards()).thenReturn(error)

            // Act
            val result = homeHearthstoneRepositoryImpl.getInfoCards()

            // Assert
            verify(cardApi, times(1)).getInfoCards()

            with(result) {
                Assert.assertEquals(ApiError.FAIL, (this as Resource.Fail).status)
                Assert.assertTrue(this.message.isNotEmpty())
            }

        } finally {
            Dispatchers.resetMain()
        }
    }

    @Test
    fun testGetInfoCardsUnknownHostException() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        try {
            // Arrange
           `when`(cardApi.getInfoCards()).thenThrow(UnknownHostException::class.java)

            // Act
            val result = homeHearthstoneRepositoryImpl.getInfoCards()

            // Assert
            verify(cardApi, times(1)).getInfoCards()

            with(result) {
                Assert.assertEquals(ApiError.NO_CONNECTION, (this as Resource.Fail).status)
                Assert.assertTrue(this.message.isNotEmpty())
            }

        } finally {
            Dispatchers.resetMain()
        }
    }

    @Test
    fun testGetInfoCardsSocketTimeoutException() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        try {
            // Arrange
            `when`(cardApi.getInfoCards()).thenThrow(SocketTimeoutException::class.java)

            // Act
            val result = homeHearthstoneRepositoryImpl.getInfoCards()

            // Assert
            verify(cardApi, times(1)).getInfoCards()

            with(result) {
                Assert.assertEquals(ApiError.TIMEOUT, (this as Resource.Fail).status)
                Assert.assertTrue(this.message.isNotEmpty())
            }

        } finally {
            Dispatchers.resetMain()
        }
    }

    @Test
    fun testGetInfoCardsException() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        try {
            // Arrange
            `when`(cardApi.getInfoCards()).thenThrow(InvalidObjectException::class.java)

            // Act
            val result = homeHearthstoneRepositoryImpl.getInfoCards()

            // Assert
            verify(cardApi, times(1)).getInfoCards()

            with(result) {
                Assert.assertEquals(ApiError.EXCEPTION, (this as Resource.Fail).status)
                Assert.assertTrue(this.message.isNotEmpty())
            }

        } finally {
            Dispatchers.resetMain()
        }
    }

}