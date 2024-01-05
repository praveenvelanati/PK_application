package com.sravan.covidapplication.Repository

import androidx.lifecycle.MutableLiveData
import com.sravan.covidapplication.Api.CasesApi
import com.sravan.covidapplication.Utils.NetworkResult
import com.sravan.covidapplication.models.ModelData
import javax.inject.Inject

class CasesRepository @Inject constructor(private val casesApi: CasesApi) {

    private val _casesLiveData = MutableLiveData<NetworkResult<ArrayList<ModelData>>>()
    val casesLiveData get() = _casesLiveData

    fun getLiveCasesData() {

        _casesLiveData.postValue(NetworkResult.Loading())
        val response = casesApi.getCases("United States")
        if (response.isSuccessful && response.body() != null){

            _casesLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null){

//            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _casesLiveData.postValue(NetworkResult.Error("Something went wrong"))        }
        else{

            _casesLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }
}