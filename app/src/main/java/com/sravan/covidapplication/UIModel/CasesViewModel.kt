package com.sravan.covidapplication.UIModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sravan.covidapplication.Repository.CasesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CasesViewModel @Inject constructor(private val casesRepository: CasesRepository) : ViewModel(){

    val casesLiveData get() = casesRepository.casesLiveData

     fun getAllCases(){

        viewModelScope.launch (Dispatchers.IO) {

            casesRepository.getLiveCasesData()
        }
    }
}
