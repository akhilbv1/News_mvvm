package org.akiapps.news.repository

import org.akiapps.news.network.Article
import org.akiapps.news.network.ResponseObject
import org.akiapps.news.network.articles
import javax.inject.Inject

class UserRepo @Inject constructor() {

    @Inject
    lateinit var remoteDataSource: RemoteDataSource

    suspend fun getIndiaArticles(onSuccess: (ArrayList<Article>) -> Unit, onFailure: (String) -> Unit) {
       remoteDataSource.getIndianBreakingNewsList({it: ResponseObject ->
           it.errorBody?.let {
               onFailure(it.message)
           } ?: kotlin.run {
               onSuccess(it.articles)
           }

       },{code:Int,message:String? ->
           onFailure(message ?: "Failure with unknown reason")
       })
    }
}