package com.alpertign.newsapp.service

import com.alpertign.newsapp.model.News
import io.reactivex.Single
import retrofit2.http.GET

interface NewsAPI {


    //https://newsapi.org/v2/top-headlines?country=tr&apiKey=e68208f405964225b699ef87ee24c249
    //BASE https://newsapi.org/v2/
    //HEAD top-headlines?country=tr&apiKey=e68208f405964225b699ef87ee24c249

    @GET("top-headlines?country=tr&apiKey=e68208f405964225b699ef87ee24c249")
    fun getNews():Single<News>

}