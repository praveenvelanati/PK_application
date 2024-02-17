package com.sravan.covidapplication.UIModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sravan.covidapplication.Repository.UserRepository
import com.sravan.covidapplication.Utils.NetworkResult
import com.sravan.covidapplication.models.Users
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    private val _usersList = MutableSharedFlow<NetworkResult<List<Users>>>()
    val usersList: SharedFlow<NetworkResult<List<Users>>> = _usersList.asSharedFlow()

    private val _content = MutableStateFlow<NetworkResult<List<Users>>>(NetworkResult.Loading())
    val content: StateFlow<NetworkResult<List<Users>>> = _content.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() {

        viewModelScope.launch {

            try {

                userRepository.fetchUsersFromDb().collect {

//                    handleResult(it)
                }
            } catch (e: Exception){

                _content.update { NetworkResult.Error(e.message) }
            }
        }
    }

    private fun handleResult(users: NetworkResult<List<Users>>) {

        _content.update {

            when(users) {
                is NetworkResult.Success -> NetworkResult.Success(it.data ?: listOf<Users>())
                is NetworkResult.Loading -> NetworkResult.Loading()
                is NetworkResult.Error -> NetworkResult.Error(it.message)
            }
        }
    }


    private fun fetchUsers() {

        viewModelScope.launch {

            _usersList.emit(NetworkResult.Loading())
            userRepository.fetchUsersFromDb().flowOn(Dispatchers.IO)
                .catch { e ->
//                    _usersList.value = NetworkResult.Error(e.message)
                    _usersList.emit(NetworkResult.Error(e.message))
                }
                .collect {
//                    _usersList.value = NetworkResult.Success(it)
                    _usersList.emit(NetworkResult.Success(it))
                }

        }
    }
}