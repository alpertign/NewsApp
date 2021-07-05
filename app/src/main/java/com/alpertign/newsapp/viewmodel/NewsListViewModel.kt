package com.alpertign.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.alpertign.newsapp.model.Article
import com.alpertign.newsapp.model.News
import com.alpertign.newsapp.service.NewsAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class NewsListViewModel(application: Application) : BaseViewModel(application) {

    private val newsApiService = NewsAPIService()
    private val disposable = CompositeDisposable()

    val articles = MutableLiveData<List<Article>>()
    val articleError = MutableLiveData<Boolean>()
    val articleLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        getDataFromAPI()
    }

    private fun getDataFromAPI(){
        articleLoading.value = true

        disposable.add(
            newsApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<News>() {
                    override fun onSuccess(t: News) {
                        showNews(t)

                    }

                    override fun onError(e: Throwable) {
                        articleLoading.value = false
                    }

                })
        )
    }

    private fun showNews(news : News){
        articles.value = news.articles
        articleError.value = false
        articleLoading.value = false
    }

}