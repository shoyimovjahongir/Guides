package com.example.data.network

import com.example.domain.mymodule1.MyData
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {


    @GET("service/v2/upcomingGuides/")
    suspend fun getUsers():Response<MyData>}