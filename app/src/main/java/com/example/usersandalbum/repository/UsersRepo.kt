package com.example.usersandalbum.repository

import com.example.usersandalbum.network.UsersApiService
import com.example.usersandalbum.pojo.AlbumsModel
import com.example.usersandalbum.pojo.UsersModel
import io.reactivex.rxjava3.core.Observable

class UsersRepo(
    private val usersApiService: UsersApiService
) {
    fun getAllUsers(): Observable<List<UsersModel>> {
        return usersApiService.getUsers()
    }

    fun getAllAlbumsOfUser(userId: Int): Observable<List<AlbumsModel>> {
        return usersApiService.getAlbums(userId)
    }
//    fun getAllAlbumsOfUser(): Observable<List<AlbumsModel>> {
//        return usersApiService.getAlbums()
//    }
}