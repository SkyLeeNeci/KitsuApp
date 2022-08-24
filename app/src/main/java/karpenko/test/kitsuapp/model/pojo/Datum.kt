package karpenko.test.kitsuapp.model.pojo

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Datum (
    @PrimaryKey
    val id: Long,
    @SerializedName("attributes")
    val animeAttributes: AnimeAttributes
        )