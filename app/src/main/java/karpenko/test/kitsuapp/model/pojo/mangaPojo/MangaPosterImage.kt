package karpenko.test.kitsuapp.model.pojo.mangaPojo

import com.google.gson.annotations.SerializedName

data class MangaPosterImage(
    @SerializedName("original")
    val original: String?
)