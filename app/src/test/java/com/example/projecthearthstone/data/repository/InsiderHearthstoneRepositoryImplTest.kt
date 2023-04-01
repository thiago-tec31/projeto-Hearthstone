package com.example.projecthearthstone.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.projecthearthstone.core.model.ApiError
import com.example.projecthearthstone.core.model.Resource
import com.example.projecthearthstone.data.mapper.FiguresCardsMapper
import com.example.projecthearthstone.data.model.response.FigureCardsResponse
import com.example.projecthearthstone.data.network.CardApi
import com.example.projecthearthstone.domain.model.CardType
import com.example.projecthearthstone.domain.model.FigureCards
import com.example.projecthearthstone.presentation.Constants.classMage
import com.example.projecthearthstone.presentation.Constants.raceOrc
import com.example.projecthearthstone.presentation.Constants.typeDruid
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
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import java.io.InvalidObjectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class InsiderHearthstoneRepositoryImplTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var cardApi: CardApi

    @Mock
    private lateinit var figuresCardsMapper: FiguresCardsMapper

    private lateinit var insiderHearthstoneRepositoryImpl: InsiderHearthstoneRepositoryImpl


    @Before
    fun setUp() {
        insiderHearthstoneRepositoryImpl = InsiderHearthstoneRepositoryImpl(
            cardApi,
            figuresCardsMapper
        )
    }

    @Test
    fun testGetFigureAllClassSuccess() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        try {
            // Arrange
            val figures = listOf(FigureCardsResponse("img.jpg"))

            val success = Response.success(figures)

            val infoCards = listOf(FigureCards("img.jpg"))

            Mockito.`when`(cardApi.getFiguresFilterByClass(classMage)).thenReturn(success)
            Mockito.`when`(figuresCardsMapper.map(figures)).thenReturn(infoCards)

            // Act
            val result = insiderHearthstoneRepositoryImpl.getFiguresAll(CardType.CLASSES, classMage)

            // Assert
            Mockito.verify(cardApi, Mockito.times(1)).getFiguresFilterByClass(classMage)

            with(result) {
                Assert.assertEquals("img.jpg", (this as Resource.Success).data[0].img)
            }

        } finally {
            Dispatchers.resetMain()
        }
    }

    @Test
    fun testGetFigureAllTypeSuccess() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        try {
            // Arrange
            val figures = listOf(FigureCardsResponse("img.jpg"))

            val success = Response.success(figures)

            val infoCards = listOf(FigureCards("img.jpg"))

            Mockito.`when`(cardApi.getFiguresFilterByType(typeDruid)).thenReturn(success)
            Mockito.`when`(figuresCardsMapper.map(figures)).thenReturn(infoCards)

            // Act
            val result = insiderHearthstoneRepositoryImpl.getFiguresAll(CardType.TYPES, typeDruid)

            // Assert
            Mockito.verify(cardApi, Mockito.times(1)).getFiguresFilterByType(typeDruid)

            with(result) {
                Assert.assertEquals("img.jpg", (this as Resource.Success).data[0].img)
            }

        } finally {
            Dispatchers.resetMain()
        }
    }

    @Test
    fun testGetFigureAllRacesSuccess() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        try {
            // Arrange
            val figures = listOf(FigureCardsResponse("img.jpg"))

            val success = Response.success(figures)

            val infoCards = listOf(FigureCards("img.jpg"))

            Mockito.`when`(cardApi.getFiguresFilterByRace(raceOrc)).thenReturn(success)
            Mockito.`when`(figuresCardsMapper.map(figures)).thenReturn(infoCards)

            // Act
            val result = insiderHearthstoneRepositoryImpl.getFiguresAll(CardType.RACES, raceOrc)

            // Assert
            Mockito.verify(cardApi, Mockito.times(1)).getFiguresFilterByRace(raceOrc)

            with(result) {
                Assert.assertEquals("img.jpg", (this as Resource.Success).data[0].img)
            }

        } finally {
            Dispatchers.resetMain()
        }
    }

    @Test
    fun testGetFigureAllClassFail() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        try {
            // Arrange
            val error: Response<List<FigureCardsResponse>> = Response.error(
                404,
                "{key:Error 404 Not Found}"
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )

            Mockito.`when`(cardApi.getFiguresFilterByClass(classMage)).thenReturn(error)

            // Act
            val result = insiderHearthstoneRepositoryImpl.getFiguresAll(CardType.CLASSES, classMage)

            // Assert
            Mockito.verify(cardApi, Mockito.times(1)).getFiguresFilterByClass(classMage)

            with(result) {
                Assert.assertEquals(ApiError.FAIL, (this as Resource.Fail).status)
                Assert.assertTrue(this.message.isNotEmpty())
            }

        } finally {
            Dispatchers.resetMain()
        }
    }

    @Test
    fun testGetFigureAllClassUnknownHostException() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        try {
            // Arrange
            Mockito.`when`(cardApi.getFiguresFilterByClass(classMage)).thenThrow(UnknownHostException::class.java)

            // Act
            val result = insiderHearthstoneRepositoryImpl.getFiguresAll(CardType.CLASSES, classMage)

            // Assert
            Mockito.verify(cardApi, Mockito.times(1)).getFiguresFilterByClass(classMage)

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
            Mockito.`when`(cardApi.getFiguresFilterByClass(classMage)).thenThrow(SocketTimeoutException::class.java)

            // Act
            val result = insiderHearthstoneRepositoryImpl.getFiguresAll(CardType.CLASSES, classMage)

            // Assert
            Mockito.verify(cardApi, Mockito.times(1)).getFiguresFilterByClass(classMage)

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
            Mockito.`when`(cardApi.getFiguresFilterByClass(classMage)).thenThrow(InvalidObjectException::class.java)

            // Act
            val result = insiderHearthstoneRepositoryImpl.getFiguresAll(CardType.CLASSES, classMage)

            // Assert
            Mockito.verify(cardApi, Mockito.times(1)).getFiguresFilterByClass(classMage)

            with(result) {
                Assert.assertEquals(ApiError.EXCEPTION, (this as Resource.Fail).status)
                Assert.assertTrue(this.message.isNotEmpty())
            }

        } finally {
            Dispatchers.resetMain()
        }
    }


}