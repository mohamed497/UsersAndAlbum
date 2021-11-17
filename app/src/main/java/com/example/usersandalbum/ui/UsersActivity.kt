package com.example.usersandalbum.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usersandalbum.R
import com.example.usersandalbum.network.UsersApi
import com.example.usersandalbum.pojo.AlbumsModel
import com.example.usersandalbum.repository.UsersRepo
import kotlinx.android.synthetic.main.activity_users.*

class UsersActivity : AppCompatActivity() {
    private lateinit var viewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        initViewModel()


        viewModel.getAlbumsUsingZip()
        viewModel.observeOnNews(this, { albums ->
            initAdapter(albums)
        })


    }

    private fun initViewModel() {
        viewModel =
            ViewModelProvider(this, UsersViewModelFactory(UsersRepo(UsersApi.retrofitService)))
                .get(UsersViewModel::class.java)
    }

    private fun initAdapter(albums: List<AlbumsModel>) {
        val usersAdapter = UsersAdapter(albums)
        userRecyclerView.adapter = usersAdapter
        usersAdapter.notifyDataSetChanged()
    }

}