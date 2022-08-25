package karpenko.test.kitsuapp.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import karpenko.test.kitsuapp.model.pojo.AnimeAttributes
import karpenko.test.kitsuapp.model.pojo.mangaPojo.MangaAttributes
import karpenko.test.kitsuapp.utils.MangaPosterTypeConverter
import karpenko.test.kitsuapp.utils.PosterImageTypeConverter

@Database(entities = [AnimeAttributes::class, MangaAttributes::class], version = 1, exportSchema = false)
@TypeConverters(PosterImageTypeConverter::class, MangaPosterTypeConverter::class)
abstract class AnimeDatabase: RoomDatabase() {
    companion object{
        @Volatile
        private var db: AnimeDatabase? = null
        private const val DB_NAME = "mainDB"
        private val LOCK = Any()

        fun getInstance(context: Context): AnimeDatabase{
            synchronized(LOCK){
                db?.let { return it }
                val instance = Room.databaseBuilder(
                    context,
                    AnimeDatabase::class.java,
                    DB_NAME
                ).build()
                db = instance
                return instance
            }

        }
    }

    abstract fun animeDao(): AnimeDao
    abstract fun mangaDao(): MangaDao
}