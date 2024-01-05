package com.sravan.covidapplication.Repository

import androidx.lifecycle.MutableLiveData
import com.sravan.covidapplication.Api.CasesApi
import com.sravan.covidapplication.models.Design
import com.sravan.covidapplication.models.Features
import javax.inject.Inject

class CustomRepository @Inject constructor(private val casesApi: CasesApi){


    fun fakeCarDesignData(): MutableLiveData<List<Design>> {

        val designsList = mutableListOf<Design>().apply {

            add(Design("Red", "SUV"))
            add(Design("Blue", "Sedan"))
        }

        val mutableDesigns = MutableLiveData<List<Design>>()
        mutableDesigns.value = designsList

        return mutableDesigns
    }

    fun fakeFeaturesData(): MutableLiveData<List<Features>>{

        val featuresList = mutableListOf<Features>().apply {

            add(Features("100RPM", "130MPH"))
            add(Features("120RPM","75MPH"))

        }

        val featuresLiveData = MutableLiveData<List<Features>>()
        featuresLiveData.value = featuresList

        return featuresLiveData
    }
}