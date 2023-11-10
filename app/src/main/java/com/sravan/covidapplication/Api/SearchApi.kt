package com.sravan.covidapplication.Api

import com.sravan.covidapplication.models.ModelData
import com.sravan.covidapplication.models.SearchModel.Search
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("places/search")
    suspend fun getSearchResults(@Query("query") searchKey: String, @Query("ll") latLng: String) : Response<Search>

}