package com.example.mvvmretrofit.repository

import com.example.mvvmretrofit.model.User
import com.example.mvvmretrofit.network.UserApiService

class UserRepository(
    private val userApiService: UserApiService
) {
    suspend fun  fetchUsers() : ArrayList<User>{
        return userApiService.fetchUsers().users
    }
}