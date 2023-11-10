package com.sravan.covidapplication.Repository

import androidx.lifecycle.MutableLiveData
import com.sravan.covidapplication.Api.SearchApi
import com.sravan.covidapplication.Api.TestApi
import com.sravan.covidapplication.Utils.NetworkResult
import com.sravan.covidapplication.models.SearchModel.Search
import javax.inject.Inject

class SearchRepository @Inject constructor(private val searchApi: SearchApi) {

    private val _searchResultsLiveData = MutableLiveData<NetworkResult<Search>>()
    val searchResultsLiveData get() = _searchResultsLiveData

    suspend fun getSearchResults(query: String){

        _searchResultsLiveData.postValue(NetworkResult.Loading())
        val response = searchApi.getSearchResults(query,"32.724290,-97.105040")

        if (response.isSuccessful && response.body() != null){

            _searchResultsLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null){
            _searchResultsLiveData.postValue(NetworkResult.Error("Something wnet Wrong..!"))
        }
        else{

            _searchResultsLiveData.postValue(NetworkResult.Error("Something went wrong..!"))
        }

    }
}