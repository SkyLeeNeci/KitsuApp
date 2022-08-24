package karpenko.test.kitsuapp.model.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "attributes")
data class AnimeAttributes(

    val description: String?,
    val canonicalTitle: String?,
    val averageRating: Double,
    val startDate: String?,
    val endDate: String?,
    val popularityRank: Int?,
    val ratingRank: Int?,
    val ageRatingGuide: String?,
    val posterImage: AnimePosterImage?,
    val episodeCount: Int?,
    val episodeLength: Int?,
    val totalLength: Int?,
    val status: String?,
    val userCount: Long?,
    val favoritesCount: Long?
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}