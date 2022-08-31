package karpenko.test.kitsuapp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import karpenko.test.kitsuapp.model.database.AnimeDatabase
import karpenko.test.kitsuapp.model.pojo.AnimeAttributes
import kotlinx.coroutines.launch

class AnimeDetailViewModel(application: Application) : BaseViewModel(application) {

    private val _animeDetailLiveData = MutableLiveData<AnimeAttributes>()
    val animeDetailLiveData: LiveData<AnimeAttributes>
        get() = _animeDetailLiveData

    fun fetch(uuid: Int) {
        launch {
            val anime = AnimeDatabase.getInstance(getApplication()).animeDao().getAnime(uuid)
            _animeDetailLiveData.value = anime
        }
    }

}