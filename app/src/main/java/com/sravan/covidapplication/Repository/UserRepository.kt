package com.sravan.covidapplication.Repository

import com.sravan.covidapplication.Api.CasesApi
import com.sravan.covidapplication.models.Users
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepository @Inject constructor(private val usersApi: CasesApi) {

    suspend fun fetchUsersFromDb(): Flow<List<Users>> = flow {

        val usersList = mutableListOf<Users>().apply {

            add(Users(1, "Sravan", "Android Developer"))
            add(Users(2, "Praveen", "iOS Developer"))
            add(Users(2, "Kyathi", "Data Analyst"))
        }

        emit(usersList)
    }
}