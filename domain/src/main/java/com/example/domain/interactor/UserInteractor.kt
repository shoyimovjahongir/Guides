package com.example.domain.interactor

import com.example.domain.mymodule1.MyData
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserInteractor @Inject constructor(private val userRepository: UserRepository) {

    fun getUsers():Flow<Result<MyData>>{
        return userRepository.getUser()
            .map {
                if (it.isSuccessful){
                    Result.success(it.body() as MyData)
                }else{
                    Result.success(it.body() as MyData)
                }
            }.catch {
                emit(Result.failure(it))
            }.flowOn(Dispatchers.IO)
    }
}