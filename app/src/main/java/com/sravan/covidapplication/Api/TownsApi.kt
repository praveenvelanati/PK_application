package com.sravan.covidapplication.Api

import com.sravan.covidapplication.models.InputModel
import com.sravan.covidapplication.models.Towns
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TownsApi {

    @POST("constituency")
    suspend fun getTownsById(@Body inputModel: InputModel): Response<List<Towns>>
}