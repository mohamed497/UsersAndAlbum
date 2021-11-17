package com.example.usersandalbum.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.usersandalbum.R
import com.example.usersandalbum.pojo.AlbumsModel
import kotlinx.android.synthetic.main.list_users_item.view.*

class UsersAdapter(private val albumsModel: List<AlbumsModel>) :
    RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.albumTitle
        val name: TextView = itemView.userName

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_users_item,
            parent, false
        )

        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = albumsModel[position]
        holder.title.text = currentItem.title
        holder.name.text = currentItem.userId.toString()
    }

    override fun getItemCount(): Int {
        return albumsModel.size
    }
}