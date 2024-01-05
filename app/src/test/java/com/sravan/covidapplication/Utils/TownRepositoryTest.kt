package com.sravan.covidapplication.Utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sravan.covidapplication.Api.TownsApi
import com.sravan.covidapplication.Repository.TownRepository
import com.sravan.covidapplication.models.InputModel
import com.sravan.covidapplication.models.Towns
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class TownRepositoryTest {

    private lateinit var townsApi: TownsApi
    private lateinit var townRepository: TownRepository

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp(){

        townsApi = mock(TownsApi::class.java)
        townRepository = TownRepository(townsApi)
    }

    @Test
    fun test_if_getTownsFunction_calls_getTownsById_function_in_townsApi() = runBlocking {

        val inputModel = InputModel(1)
        val expectedResponse: Response<List<Towns>> = Response.success(listOf<Towns>())

        Mockito.`when`(townsApi.getTownsById(inputModel)).thenReturn(expectedResponse)

        val actualResponse = townRepository.getTowns(inputModel)

        Assert.assertEquals(expectedResponse,actualResponse)
    }
}