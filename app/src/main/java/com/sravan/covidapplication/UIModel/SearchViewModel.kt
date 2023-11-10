package com.sravan.covidapplication.UIModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sravan.covidapplication.Repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository): ViewModel() {

    val searchLiveData get() = searchRepository.searchResultsLiveData

    fun getSearchResults(query: String){

        viewModelScope.launch {

            searchRepository.getSearchResults(query)
        }
    }
}