package com.example.projecthearthstone.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.projecthearthstone.core.model.ApiError
import com.example.projecthearthstone.core.model.Resource
import com.example.projecthearthstone.domain.model.InfoCards
import com.example.projecthearthstone.domain.usecase.HomeHearthstoneUseCase
import com.example.projecthearthstone.presentation.Constants.classMage
import com.example.projecthearthstone.presentation.Constants.noConnection
import com.example.projecthearthstone.presentation.Constants.raceOrc
import com.example.projecthearthstone.presentation.Constants.typeDruid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times

import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeHearthstoneViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var homeHearthstoneUseCase: HomeHearthstoneUseCase

    private lateinit var homeHearthstoneViewModel: HomeHearthstoneViewModel


    @Before
    fun setUp() {
        homeHearthstoneViewModel = HomeHearthstoneViewModel(
            homeHearthstoneUseCase
        )
    }


    @Test
    fun testGetInfoCardsReturnFail() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        try {

            // Arrange
            Mockito.`when`(homeHearthstoneUseCase.getInfoCards()).thenReturn(mockResourceWithFail())

            // Act
            homeHearthstoneViewModel.getInfoCards()

            // Assert
            Mockito.verify(homeHearthstoneUseCase, times(1)).getInfoCards()
            with(homeHearthstoneViewModel.apiError.value) {
                Assert.assertEquals(noConnection, this?.message)
                Assert.assertEquals(ApiError.TIMEOUT, this?.status)
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
            Mockito.`when`(homeHearthstoneUseCase.getInfoCards()).thenReturn(mockResourceWithSuccess())

            // Act
            homeHearthstoneViewModel.getInfoCards()

            // Assert
            Mockito.verify(homeHearthstoneUseCase, times(1)).getInfoCards()
            homeHearthstoneViewModel.cards.value.apply {
                Assert.assertEquals(classMage, this?.classes?.get(0))
                Assert.assertEquals(typeDruid, this?.types?.get(0))
                Assert.assertEquals(raceOrc, this?.races?.get(0))
            }

        } finally {
            Dispatchers.resetMain()
        }
    }

    private fun mockResourceWithFail() = Resource.Fail(ApiError.TIMEOUT, noConnection)

    private fun mockResourceWithSuccess() = Resource.Success(
        InfoCards(
            arrayListOf(classMage),
            arrayListOf(typeDruid),
            arrayListOf(raceOrc)
        )
    )

}