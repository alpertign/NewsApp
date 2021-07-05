package com.alpertign.newsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alpertign.newsapp.model.Article
import com.alpertign.newsapp.model.Source

class NewsDetailsViewModel : ViewModel(){


    val articleLiveData = MutableLiveData<Article>()

    fun getDataFromRoom(){
        val article = Article("alp","content","description","publisedAt", Source(null,"alp"),"Title","www.ss.com","www.ss.com")
        articleLiveData.value = article
    }
}