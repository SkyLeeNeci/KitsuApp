package karpenko.test.kitsuapp.model.api

import io.reactivex.rxjava3.core.Single
import karpenko.test.kitsuapp.model.pojo.mangaPojo.MangaSource
import retrofit2.http.GET
import retrofit2.http.Query

interface MangaApiService {

    @GET("manga")
    fun getMangaList(
        @Query("page[limit]") pageLimit: Int = MANGA_LIST_LIMIT
    ): Single<MangaSource>


    companion object {
        private const val MANGA_LIST_LIMIT = 20
        private const val DEFAULT_SORT = "popularityRank"
    }
}