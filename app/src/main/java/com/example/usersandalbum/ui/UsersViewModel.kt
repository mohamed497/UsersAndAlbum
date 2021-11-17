package com.example.usersandalbum.ui

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.example.usersandalbum.pojo.AlbumsModel
import com.example.usersandalbum.pojo.UsersModel
import com.example.usersandalbum.repository.UsersRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList

class UsersViewModel(private val usersRepo: UsersRepo) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()


    private val albums = MutableLiveData<List<AlbumsModel>>()


    fun observeOnNews(lifecycle: LifecycleOwner, news: Observer<List<AlbumsModel>>) {
        albums.observe(lifecycle, news)
    }

    private fun getSingleAlbum(user: UsersModel): Observable<List<AlbumsModel>> {
        return usersRepo.getAllAlbumsOfUser(user.id)
    }

    fun getAlbumsUsingZip() {
        usersRepo.getAllUsers()
            .flatMap { users ->
                val album = users.map(::getSingleAlbum)
                return@flatMap Observable.just(album)
            }.flatMap { listOfObservableOfAlbum ->
                Observable.zip(listOfObservableOfAlbum, Function { args ->
                    val result = ArrayList<AlbumsModel>()
                    for (obj in args) {
                        val album = obj as? AlbumsModel
                        album?.let { album ->
                            result.add(album) }
                    }
//                    albums.postValue(result)
                    return@Function result
                }, true, 1)
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ albumList ->
                Log.d("UserViewModel_Size", albumList.size.toString())

                albums.value = albumList
            }, { throwable ->
                Log.d("UserViewModel", throwable.toString())
            }, {
                Log.d("UserViewModel", "Albums_Completed")
            }
            )

    }
    

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}