package org.tumo.myapplication.repository

import org.tumo.myapplication.retrofit.NewsApiService
import org.tumo.myapplication.retrofit.RetrofitHelper
import kotlin.random.Random

class DataLoaderRepository {
    fun getData(): List<String> {
        return listOf(
            "one",
            "2t",
            "4",
            Random(100).toString(),
            Random(200).toString()
        )
    }

    fun getImages(): List<String>? {
        val url =
            "https://imgs.search.brave.com/_WeRILENZZFx6eKV9kucdS5BKhWdlAmvtWsy2flVs8o/rs:fit:860:0:0/g:ce/aHR0cHM6Ly9tZWRp/YS5pc3RvY2twaG90/by5jb20vaWQvMTI5/NzE1OTM2NS9waG90/by9wb3J0cmFpdC1v/Zi15b3VuZy1zbWls/aW5nLXdvbWFuLWZh/Y2UtcGFydGlhbGx5/LWNvdmVyZWQtd2l0/aC1mbHlpbmctaGFp/ci1pbi13aW5keS1k/YXktc3RhbmRpbmcu/anBnP3M9NjEyeDYx/MiZ3PTAmaz0yMCZj/PUkxNmNfWnpRSEVl/R29QVVZyVlA5cFB1/c1N6c215bXZWS2RR/VmdQdVZkRG89"
        return listOf<String>(
            url, url, url, url
        )
    }

    suspend fun loadNews(): NewsResponse {
        val apiService = RetrofitHelper.getRetrofit().create(NewsApiService::class.java)
        return apiService.fetchArticles(
            "car",
            "2023-10-10",
            "publishedAt",
            "dfa28781f7eb46f8b33fdd642153b51c"
        )
    }


}
