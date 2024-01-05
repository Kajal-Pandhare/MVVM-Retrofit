package com.example.mvvmretrofit.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmretrofit.R
import com.example.mvvmretrofit.adapter.UserAdapter
import com.example.mvvmretrofit.databinding.UserFragmentBinding
import com.example.mvvmretrofit.factory.MyViewModelFactory
import com.example.mvvmretrofit.network.UserApiService
import com.example.mvvmretrofit.repository.UserRepository
import com.example.mvvmretrofit.viewmodel.UserViewModel

class Userfragment:Fragment() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var userRepository: UserRepository
    private lateinit var userAdapter: UserAdapter

    private lateinit var binding: UserFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UserFragmentBinding.inflate(layoutInflater)

        initViews()
        initViewModels()
        initAdapter()
        initObserver()

        userViewModel.fetchUsers()
        return binding.root
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun initObserver(){
        userViewModel.usersUpdatedAvailableLiveData.observe(
            viewLifecycleOwner
        ){
            if (it){
                userAdapter.notifyDataSetChanged()
            }
        }
    }
    private fun initAdapter(){
        userAdapter = UserAdapter(userViewModel.users)
        binding.recyclerUsers.adapter = userAdapter
    }
    private fun initViewModels(){
        userViewModel = ViewModelProvider(
            this,
            MyViewModelFactory(
                UserRepository(
                    UserApiService.getInstance()
                )
            )
        ).get(UserViewModel::class.java)
    }
    private fun initViews(){
        binding.recyclerUsers.layoutManager =
             LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
    }
}