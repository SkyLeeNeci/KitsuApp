package karpenko.test.kitsuapp.model.pojo

import com.google.gson.annotations.SerializedName

data class AnimeSource(
    @SerializedName("data")
    val data: List<Datum>
)