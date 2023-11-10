package com.sravan.covidapplication.UIModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sravan.covidapplication.Repository.EntryRepository
import com.sravan.covidapplication.models.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EntryViewModel @Inject constructor(private val entryRepository: EntryRepository): ViewModel() {

   val entryLiveData get() = entryRepository.entryLiveData
    val profile = Profile("Sravan", false)

    fun getData(){

        viewModelScope.launch {

            entryRepository.getEntryData()
        }
    }

    fun updateProfile(isOnline: Boolean){

        profile.isOnline = isOnline
    }

}