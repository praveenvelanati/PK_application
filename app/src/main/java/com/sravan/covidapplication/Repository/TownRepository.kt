package com.sravan.covidapplication.Repository

import com.sravan.covidapplication.Api.TownsApi
import com.sravan.covidapplication.models.InputModel
import com.sravan.covidapplication.models.Towns
import retrofit2.Response
import javax.inject.Inject

class TownRepository @Inject constructor(private val townsApi: TownsApi) {

    suspend fun getTowns(inputModel: InputModel): Response<List<Towns>> {

        return townsApi.getTownsById(inputModel)

    }
}