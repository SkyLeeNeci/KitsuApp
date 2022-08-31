package karpenko.test.kitsuapp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import karpenko.test.kitsuapp.model.database.AnimeDatabase
import karpenko.test.kitsuapp.model.pojo.mangaPojo.MangaAttributes
import kotlinx.coroutines.launch

class MangaDetailViewModel(application: Application) : BaseViewModel(application) {

    private val _mangaDetailLiveData = MutableLiveData<MangaAttributes>()
    val mangaDetailLiveData: LiveData<MangaAttributes>
        get() = _mangaDetailLiveData

    fun fetch(mangaId: Int) {

        launch {
            val manga = AnimeDatabase.getInstance(getApplication()).mangaDao().getManga(mangaId)
            _mangaDetailLiveData.value = manga
        }

    }

}