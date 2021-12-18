package com.example.domain.repository

import com.example.domain.mymodule1.MyData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


interface UserRepository {

    fun getUser(): Flow<Response<MyData>>
}