package karpenko.test.kitsuapp.model.pojo.mangaPojo

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class MangaDatum(
    @PrimaryKey
    val id: Long,
    @SerializedName("attributes")
    val mangaAttributes: MangaAttributes
)