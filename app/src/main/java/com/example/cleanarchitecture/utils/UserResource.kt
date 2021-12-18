package com.example.cleanarchitecture.utils

import com.example.domain.mymodule1.MyData


sealed class UserResource{

    object Loading:UserResource()
    data class Error(val message:String):UserResource()
    data class Success(val list: MyData?):UserResource()

}
