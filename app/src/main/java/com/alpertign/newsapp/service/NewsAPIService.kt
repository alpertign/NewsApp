package com.alpertign.newsapp.service

import com.alpertign.newsapp.model.News
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NewsAPIService {

    //BASE https://newsapi.org/v2/
    //HEAD top-headlines?country=tr&apiKey=e68208f405964225b699ef87ee24c249

    private val BASE_URL = "https://newsapi.org/v2/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(NewsAPI::class.java)

    fun getData() : Single<News>{
        return api.getNews()
    }
}