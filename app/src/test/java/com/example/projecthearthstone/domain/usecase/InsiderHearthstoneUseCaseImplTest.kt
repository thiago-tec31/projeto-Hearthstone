package com.example.projecthearthstone.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.projecthearthstone.core.model.ApiError
import com.example.projecthearthstone.core.model.Resource
import com.example.projecthearthstone.domain.model.CardType
import com.example.projecthearthstone.domain.repository.InsiderHearthstoneRepository
import com.example.projecthearthstone.presentation.Constants.classMage
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
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class InsiderHearthstoneUseCaseImplTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var insiderHearthstoneRepository: InsiderHearthstoneRepository

    private lateinit var insiderHearthstoneUseImpl: InsiderHearthstoneUseCaseImpl

    @Before
    fun setup() {
        insiderHearthstoneUseImpl = InsiderHearthstoneUseCaseImpl(
            insiderHearthstoneRepository
        )
    }

    @Test
    fun testGetFiguresAllWithParamsEmpty() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        try {
            // Act
            val result = insiderHearthstoneUseImpl.getFiguresAll(null, null)

            // Assert
            Assert.assertTrue(result is Resource.Fail)

            with(result as Resource.Fail) {
                Assert.assertEquals(ApiError.EXCEPTION, this.status)
                Assert.assertEquals(InsiderHearthstoneUseCaseImpl.MSG_PARAM_EMPTY, this.message)
            }


        } finally {
            Dispatchers.resetMain()
        }
    }

    @Test
    fun testGetFiguresAll() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        try {
            // Act
            insiderHearthstoneUseImpl.getFiguresAll(CardType.CLASSES, classMage)

            // Assert
            Mockito.verify(insiderHearthstoneRepository, times(1)).getFiguresAll(CardType.CLASSES, classMage)
        } finally {
            Dispatchers.resetMain()
        }
    }
}