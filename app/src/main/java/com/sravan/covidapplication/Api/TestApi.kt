package com.sravan.covidapplication.Api

import com.sravan.covidapplication.models.Entry
import retrofit2.Response
import retrofit2.http.GET

interface TestApi {

    @GET("entries")
    suspend fun getEntryData(): Response<Entry>
}