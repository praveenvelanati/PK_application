package com.sravan.covidapplication.Repository

import androidx.lifecycle.MutableLiveData
import com.sravan.covidapplication.Api.TestApi
import com.sravan.covidapplication.Api.TestDao
import com.sravan.covidapplication.Utils.NetworkResult
import com.sravan.covidapplication.models.Entry
import javax.inject.Inject

class EntryRepository @Inject constructor(private val testApi: TestApi) {

    private val _entryLiveData = MutableLiveData<NetworkResult<Entry>>()
    val entryLiveData get() = _entryLiveData

    suspend fun getEntryData() {

        val data = testApi.getEntryData()

        if (data.isSuccessful && data.body() != null) {

            _entryLiveData.postValue(NetworkResult.Success(data.body()!!))
//            testDao.insertAll(data.body()!!.entries)

        } else {

            // Failed case
            _entryLiveData.postValue(NetworkResult.Error("failed"))

        }
    }

}