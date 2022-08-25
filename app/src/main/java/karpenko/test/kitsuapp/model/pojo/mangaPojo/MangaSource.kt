package karpenko.test.kitsuapp.model.pojo.mangaPojo

import com.google.gson.annotations.SerializedName
import karpenko.test.kitsuapp.model.pojo.Datum

data class MangaSource(
    @SerializedName("data")
    val data: List<MangaDatum>
)