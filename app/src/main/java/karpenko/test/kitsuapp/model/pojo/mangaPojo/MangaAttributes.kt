package karpenko.test.kitsuapp.model.pojo.mangaPojo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mangaAttributes")
data class MangaAttributes(

    val canonicalTitle: String?,
    val averageRating: Double?,
    val userCount: Int?,
    val favoritesCount: Int?,
    val startDate: String?,
    val endDate: String?,
    val popularityRank: Int?,
    val ratingRank: Int?,
    val ageRating: String?,
    val posterImage: MangaPosterImage?

){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}