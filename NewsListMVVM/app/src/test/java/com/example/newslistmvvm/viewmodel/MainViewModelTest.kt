package com.example.newslistmvvm.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.newslistmvvm.models.NewsListItem
import com.example.newslistmvvm.repository.NewsRepository
import com.example.newslistmvvm.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.Assert.*

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: NewsRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun test_GetProducts() = runTest{
        Mockito.`when`(repository.getNews("techcrunch", "3d0b4a92c76f4b279081bf91e8df8c13")).thenReturn(
            NetworkResult.Success(NewsListItem(emptyList(),"ok",10)))

        val sut = MainViewModel(repository)
        sut.getNews()
        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.news.value
        if (result != null) {
            Assert.assertEquals(0, result.data?.articles?.size)
        }
    }

    @Test
    fun test_GetProduct_expectedError() = runTest{
        Mockito.`when`(repository.getNews("techcrunch", "3d0b4a92c76f4b279081bf91e8df8c13")).thenReturn(NetworkResult.Error("Something Went Wrong"))

        val sut = MainViewModel(repository)
        sut.getNews()
        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.news.value
        assertEquals(true, result is NetworkResult.Error)
        if (result != null) {
            assertEquals("Something Went Wrong", result.message)
        }
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}