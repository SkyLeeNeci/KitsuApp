package karpenko.test.kitsuapp.model.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object AnimeApiFactory {

    private const val BASE_URL = "https://kitsu.io/api/edge/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val animeApiService = retrofit.create(AnimeApiService::class.java)
    val mangaApiService = retrofit.create(MangaApiService::class.java)
}