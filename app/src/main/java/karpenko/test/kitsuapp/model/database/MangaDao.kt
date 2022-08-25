package karpenko.test.kitsuapp.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import karpenko.test.kitsuapp.model.pojo.mangaPojo.MangaAttributes

@Dao
interface MangaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimeList(vararg mangaList: MangaAttributes): List<Long>

    @Query("select * from mangaAttributes")
    suspend fun getAllManga(): List<MangaAttributes>

    @Query("select * from mangaAttributes where id = :id")
    suspend fun getManga(id: Int): MangaAttributes

    @Query("Delete from mangaAttributes")
    suspend fun deleteAllManga()

    @Query("select * from mangaAttributes order by ratingRank")
    suspend fun getAllMangaOrderByRating(): List<MangaAttributes>

}