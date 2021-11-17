package com.example.usersandalbum.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.usersandalbum.repository.UsersRepo

class UsersViewModelFactory(private val usersRepo: UsersRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            return UsersViewModel(usersRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}