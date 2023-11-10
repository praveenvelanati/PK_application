package com.sravan.covidapplication.Api

import com.sravan.covidapplication.models.ModelData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CasesApi {

    @GET("search?")
    fun getCases(@Query("country") country: String) :Response<ArrayList<ModelData>>
    
}