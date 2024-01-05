package com.example.mvvmretrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmretrofit.R
import com.example.mvvmretrofit.model.User

class UserAdapter(private val users : ArrayList<User>):
RecyclerView.Adapter<UserAdapter.UserViewModelHolder>(){

    inner class UserViewModelHolder( view: View):RecyclerView.ViewHolder(view){
        val imgUser : ImageView
        val txtId : TextView
        val txtuserName : TextView
        val txtEmail : TextView
        val txtAvatar : TextView

        init {
            imgUser = view.findViewById(R.id.img)
            txtId = view.findViewById(R.id.txtId)
            txtuserName = view.findViewById(R.id.txtUserName)
            txtEmail = view.findViewById(R.id.txtEmail)
            txtAvatar = view.findViewById(R.id.txtAvatar)
        }

    }

    override fun getItemCount() = users.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewModelHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_view,null)

        return UserViewModelHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewModelHolder, position: Int) {
        val user = users[position]
        holder.txtId.text = user.id.toString()
        holder.txtuserName.text = "${user.firstName} ${user.lastName}"
        holder.txtEmail.text = user.email
        holder.txtAvatar.text = user.avatar

        Glide.with(holder.itemView)
            .load(user.avatar)
            .error(R.mipmap.ic_launcher)
            .circleCrop()
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.imgUser)
    }
}