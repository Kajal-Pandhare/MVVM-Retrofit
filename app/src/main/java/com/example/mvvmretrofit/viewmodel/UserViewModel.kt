package com.example.mvvmretrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmretrofit.model.User
import com.example.mvvmretrofit.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(private val userRepository: UserRepository
):ViewModel() {

    val usersUpdatedAvailableLiveData = MutableLiveData<Boolean>()
    val users = ArrayList<User>()

    //var pageNumber : Int = 0
    fun fetchUsers(){
        CoroutineScope(Dispatchers.IO).launch {
            val users = userRepository.fetchUsers()

            withContext(Dispatchers.Main){
                this@UserViewModel.users.addAll(users)
                usersUpdatedAvailableLiveData.postValue(true)
            }
        }
    }

}