package com.example.projecthearthstone.presentation.insider

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.projecthearthstone.core.model.ApiError
import com.example.projecthearthstone.core.model.Resource
import com.example.projecthearthstone.domain.model.CardType
import com.example.projecthearthstone.domain.model.FigureCards
import com.example.projecthearthstone.domain.usecase.InsiderHearthstoneUseCase
import com.example.projecthearthstone.presentation.Constants.classMage
import com.example.projecthearthstone.presentation.Constants.noConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class InsiderHeartstoneViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var insiderHearthstoneUseCase: InsiderHearthstoneUseCase

    private lateinit var insiderHeartstoneViewModel: InsiderHeartstoneViewModel

    @Before
    fun setUp() {
        insiderHeartstoneViewModel = InsiderHeartstoneViewModel(
            insiderHearthstoneUseCase
        )
    }

    private val images = arrayListOf(
        FigureCards("img.jpg"),
        FigureCards("img2.jpg")
    )

    @Test
    fun testGetFiguresReturnFail() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        try {
            // Arrange
            Mockito.`when`(insiderHearthstoneUseCase.getFiguresAll(CardType.CLASSES, classMage))
                .thenReturn(mockResourceWithFail())

            // Act
            insiderHeartstoneViewModel.getFigures(CardType.CLASSES, classMage)

            // Assert
            Mockito.verify(insiderHearthstoneUseCase, Mockito.times(1))
                .getFiguresAll(CardType.CLASSES, classMage)

            insiderHeartstoneViewModel.figures.value.apply {
                Assert.assertNotNull(this)
                Assert.assertTrue(this?.isEmpty() == true)
            }

        } finally {
            Dispatchers.resetMain()
        }
    }

    @Test
    fun testGetInfoCardsReturnSuccess() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        try {
            // Arrange
            Mockito.`when`(insiderHearthstoneUseCase.getFiguresAll(CardType.CLASSES, classMage))
                .thenReturn(mockResourceWithSuccess())

            // Act
            insiderHeartstoneViewModel.getFigures(CardType.CLASSES, classMage)

            // Assert
            Mockito.verify(insiderHearthstoneUseCase, Mockito.times(1))
                .getFiguresAll(CardType.CLASSES, classMage)

            insiderHeartstoneViewModel.figures.value.apply {
                Assert.assertTrue(this?.size == 2)
            }

        } finally {
            Dispatchers.resetMain()
        }
    }

    private fun mockResourceWithFail() = Resource.Fail(ApiError.TIMEOUT, noConnection)

    private fun mockResourceWithSuccess() = Resource.Success(images)

}