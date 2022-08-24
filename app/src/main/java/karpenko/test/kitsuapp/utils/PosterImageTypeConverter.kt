package karpenko.test.kitsuapp.utils

import androidx.room.TypeConverter
import karpenko.test.kitsuapp.model.pojo.AnimePosterImage
import org.json.JSONObject

class PosterImageTypeConverter {

    @TypeConverter
    fun fromSource(animePosterImage: AnimePosterImage): String {
        return JSONObject().apply {
            put("original", animePosterImage.original)
        }.toString()
    }

    @TypeConverter
    fun toSource(animePosterImage: String): AnimePosterImage {
        val json = JSONObject(animePosterImage)
        return AnimePosterImage( json.getString("original"))
    }

}