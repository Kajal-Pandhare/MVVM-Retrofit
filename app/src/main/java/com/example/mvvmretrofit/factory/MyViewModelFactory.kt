package com.example.mvvmretrofit.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmretrofit.repository.UserRepository
import com.example.mvvmretrofit.viewmodel.UserViewModel

class MyViewModelFactory(private val userRepository: UserRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(userRepository) as T
    }
}