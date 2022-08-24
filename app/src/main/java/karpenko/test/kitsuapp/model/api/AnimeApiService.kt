package karpenko.test.kitsuapp.model.api

import io.reactivex.rxjava3.core.Single
import karpenko.test.kitsuapp.model.pojo.AnimeSource
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeApiService {

    @GET("anime")
    fun getAnimeList(
        @Query("page[limit]") pageLimit: Int = ANIME_LIST_LIMIT
    ): Single<AnimeSource>

    @GET("anime")
    fun getAnimeListSortedByPopularity(
        @Query("page[limit]") pageLimit: Int = ANIME_LIST_LIMIT,
        @Query("page[offset]") pageOffset: Int = 1,
        @Query("sort") sortByAttribute: String = DEFAULT_SORT
    ): Single<AnimeSource>

    @GET("anime/{id}")
    fun getAnimeByID(
        @Path("id") id: Int
    ): Single<AnimeSource>

    @GET("anime")
    fun getAnimeByCategory(
        @Query("filter[categories]") categoryFilter: String
    ): Single<AnimeSource>

    @GET("anime")
    fun getAnimeByName(
        @Query("filter[text]") nameFilter: String
    ): Single<AnimeSource>

    companion object {
        private const val ANIME_LIST_LIMIT = 20
        private const val DEFAULT_SORT = "popularityRank"
    }
}