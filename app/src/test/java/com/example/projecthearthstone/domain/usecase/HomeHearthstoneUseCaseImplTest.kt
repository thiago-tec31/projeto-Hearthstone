package com.example.projecthearthstone.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.projecthearthstone.domain.repository.HomeHearthstoneRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
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
class HomeHearthstoneUseCaseImplTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var homeHearthstoneRepository: HomeHearthstoneRepository

    private lateinit var homeHearthstoneUseCaseImpl: HomeHearthstoneUseCaseImpl

    @Before
    fun setup() {
        homeHearthstoneUseCaseImpl = HomeHearthstoneUseCaseImpl(
            homeHearthstoneRepository
        )
    }

    @Test
    fun testGetInfoCards() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        try {
            // Act
            homeHearthstoneUseCaseImpl.getInfoCards()

            // Assert
            Mockito.verify(homeHearthstoneRepository, times(1))
                .getInfoCards()
        } finally {
            Dispatchers.resetMain()
        }
    }

}