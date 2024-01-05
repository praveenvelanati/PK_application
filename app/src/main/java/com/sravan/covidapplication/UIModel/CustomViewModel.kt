package com.sravan.covidapplication.UIModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.sravan.covidapplication.Repository.CustomRepository
import com.sravan.covidapplication.models.Design
import com.sravan.covidapplication.models.Features
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CustomViewModel @Inject constructor(val customRepository: CustomRepository) : ViewModel() {

    private var _carLiveData = MediatorLiveData<Pair<List<Design>, List<Features>>>()
    val carsLiveData: LiveData<Pair<List<Design>, List<Features>>> get() = _carLiveData

    //https://stackoverflow.com/questions/67436014/how-can-i-observe-two-livedata-and-use-results-together-how-to-implement-mediat
//https://medium.com/androiddevelopers/livedata-beyond-the-viewmodel-reactive-patterns-using-transformations-and-mediatorlivedata-fda520ba00b7
    fun getCarsData() {

        val designLiveData = customRepository.fakeCarDesignData()
        val featureLiveData = customRepository.fakeFeaturesData()


        _carLiveData.addSource(designLiveData){ designs ->
            val features = featureLiveData.value
            if (features != null){
                _carLiveData.value = Pair(designs, features)
            }

        }

        _carLiveData.addSource(featureLiveData) {features ->

            val design = designLiveData.value
            if (design != null){
                _carLiveData.value = Pair(design, features)
            }
        }

    }
}

