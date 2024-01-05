package com.example.mvvmretrofit.network

import com.example.mvvmretrofit.model.UserResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiService{

    @GET("/api/users?page=2")
    suspend fun fetchUsers(
    ) : UserResponse

    companion object{
        private var userApiService : UserApiService? = null

        fun getInstance():UserApiService{
            if (userApiService == null){

              val retrofit =   Retrofit.Builder()
                    .baseUrl("https://reqres.in/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                userApiService = retrofit.create(UserApiService::class.java)
            }
            return userApiService!!
        }
    }
}