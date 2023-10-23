package org.tumo.myapplication.retrofit

import org.tumo.myapplication.repository.NewsResponse
import org.tumo.myapplication.retrofit.RetrofitHelper.getRetrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/everything")
    suspend fun fetchArticles(
        @Query("q") query: String,
        @Query("from") fromDate: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse

    @GET("v2/top-headlines")
    suspend fun fetchHeadlines(): NewsResponse
}



