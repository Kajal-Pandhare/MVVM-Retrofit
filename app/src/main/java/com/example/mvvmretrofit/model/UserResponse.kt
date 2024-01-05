package com.example.mvvmretrofit.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data")
    val users : ArrayList<User>
)
