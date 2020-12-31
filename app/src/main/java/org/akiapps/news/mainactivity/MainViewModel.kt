package org.akiapps.news.mainactivity

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.akiapps.news.network.Article
import org.akiapps.news.network.articles
import org.akiapps.news.repository.UserRepo
import org.akiapps.news.viewmodelextensions.ViewModelAssistedFactory
import javax.inject.Inject

class MainViewModel(private val userRepo: UserRepo,val args:SavedStateHandle?):ViewModel() {

    private val TAG:String = MainViewModel::class.java.simpleName

    private val articles: MutableLiveData<ArrayList<Article>> = MutableLiveData()

    private val errorLiveData: MutableLiveData<String> = MutableLiveData()


    fun fetchArticles(){
        Log.i(TAG,"Args is ${args?.getLiveData<ArrayList<Article>>("Articles")?.value?.toList()}")
        articles.value?.let {
            articles.value = it
        }?: kotlin.run {
            viewModelScope.launch {
                userRepo.getIndiaArticles({
                    articles.value = it
                   setSate(it)
                },{
                    errorLiveData.value = it
                })
            }
        }

    }

    fun setSate(list:ArrayList<Article>){
        args?.set("Articles",list)
    }

    fun getArticles():MutableLiveData<ArrayList<Article>> = articles

    fun getErrorLiveData() = errorLiveData

    override fun onCleared() {
        super.onCleared()
        Log.i(MainActivity::class.java.simpleName,"Viewmodel is cleared")
    }

}

class MainViewModelFactory @Inject constructor(val userRepo: UserRepo):ViewModelAssistedFactory<MainViewModel>{
    override fun create(handle: SavedStateHandle): MainViewModel {
        return MainViewModel(userRepo,handle)
    }
}