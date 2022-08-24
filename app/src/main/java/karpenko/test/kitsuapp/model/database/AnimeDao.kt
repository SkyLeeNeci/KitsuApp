package karpenko.test.kitsuapp.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import karpenko.test.kitsuapp.model.pojo.AnimeAttributes
import karpenko.test.kitsuapp.model.pojo.Datum

@Dao
interface AnimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimeList(vararg animeList: AnimeAttributes): List<Long>

    @Query("select * from attributes")
    suspend fun getAllAnime(): List<AnimeAttributes>

    @Query("select * from attributes where id = :id")
    suspend fun getAnime(id: Int): AnimeAttributes

    @Query("Delete from attributes")
    suspend fun deleteAllAnime()

    @Query("select * from attributes order by ratingRank")
    suspend fun getAllAnimeOrderByRating(): List<AnimeAttributes>

}