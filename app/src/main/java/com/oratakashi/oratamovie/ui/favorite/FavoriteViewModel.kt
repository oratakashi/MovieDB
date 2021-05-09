package com.oratakashi.oratamovie.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.oratakashi.oratamovie.di.wire.Fav
import com.oratakashi.oratamovie.domain.model.fav.Favorite
import com.oratakashi.oratamovie.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    @Fav private val useCase: UseCase,
    private val disposable: CompositeDisposable
) : ViewModel() {
    private val keyword: MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    val data : LiveData<PagedList<Favorite>> by lazy {
        Transformations.switchMap(keyword) {
            if(it == null || it.isEmpty()){
                useCase.getFavoritePaging()
            }else{
                useCase.getFavoriteSearchPaging(it)
            }
        }
    }

    fun getAll(){
        keyword.postValue("")
    }

    fun search(keyword: String){
        this.keyword.postValue(keyword)
    }

    fun delete(data: Favorite){
        useCase.deleteFav(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toFlowable()
            .subscribe()
            .let(disposable::add)
    }
}