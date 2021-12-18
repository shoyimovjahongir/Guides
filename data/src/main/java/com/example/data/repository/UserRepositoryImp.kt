package com.example.data.repository

import com.example.data.network.ApiService
import com.example.domain.mymodule1.MyData
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(private val apiService: ApiService) : UserRepository {
    override fun getUser(): Flow<Response<MyData>> {
        return flow { emit(apiService.getUsers()) }
    }

}