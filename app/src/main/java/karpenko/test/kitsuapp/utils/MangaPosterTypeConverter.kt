package karpenko.test.kitsuapp.utils

import androidx.room.TypeConverter
import karpenko.test.kitsuapp.model.pojo.mangaPojo.MangaPosterImage
import org.json.JSONObject

class MangaPosterTypeConverter {

    @TypeConverter
    fun fromSource(mangaPosterImage: MangaPosterImage): String {
        return JSONObject().apply {
            put("original", mangaPosterImage.original)
        }.toString()
    }

    @TypeConverter
    fun toSource(mangaPosterImage: String): MangaPosterImage {
        val json = JSONObject(mangaPosterImage)
        return MangaPosterImage(json.getString("original"))
    }

}