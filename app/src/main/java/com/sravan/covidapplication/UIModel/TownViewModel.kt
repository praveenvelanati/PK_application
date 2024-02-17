package com.sravan.covidapplication.UIModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sravan.covidapplication.Repository.TownRepository
import com.sravan.covidapplication.Utils.NetworkResult
import com.sravan.covidapplication.models.InputModel
import com.sravan.covidapplication.models.Towns
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TownViewModel @Inject constructor(private val townRepository: TownRepository): ViewModel() {

    private var _townsList = MutableLiveData<NetworkResult<List<Towns>>>()
    val townsList:MutableLiveData<NetworkResult<List<Towns>>> get() = _townsList
    var isDataLoaded = false

    fun getTownsList(inputModel: InputModel) = viewModelScope.launch (Dispatchers.IO) {

        val dataList = mutableListOf<Towns>().apply {

            add(Towns(1,2,"HH","Sravan", "Active"))
            add(Towns(1,2,"HH","Sravan", "Active"))
            add(Towns(1,2,"HH","Sravan", "Active"))
            add(Towns(1,2,"HH","Sravan", "Active"))

        }


            _townsList.postValue(NetworkResult.Success(dataList))


    }



    override fun onCleared() {
        super.onCleared()
        _townsList.value = null!!
    }

}