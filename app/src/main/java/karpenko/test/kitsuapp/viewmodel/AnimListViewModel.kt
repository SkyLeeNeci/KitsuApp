package karpenko.test.kitsuapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import karpenko.test.kitsuapp.model.api.AnimeApiFactory
import karpenko.test.kitsuapp.model.database.AnimeDatabase
import karpenko.test.kitsuapp.model.pojo.AnimeAttributes
import kotlinx.coroutines.launch

class AnimListViewModel(application: Application) : BaseViewModel(Application()) {

    val dao = AnimeDatabase.getInstance(application).animeDao()
    private val compositeDisposable = CompositeDisposable()

    private val _listOfAnimeLiveData = MutableLiveData<List<AnimeAttributes>>()
    val listOfAnimeLiveData: LiveData<List<AnimeAttributes>>
        get() = _listOfAnimeLiveData

    private val _animeLoadErrorLiveData = MutableLiveData<Boolean>()
    val animeLoadErrorLiveData: LiveData<Boolean>
        get() = _animeLoadErrorLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData


    fun loadDataFromRemote() {
        val disposable = AnimeApiFactory.animeApiService.getAnimeList()
            .map { it.data.map { it.animeAttributes } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                saveAnimeListLocal(it)
                Log.d("TEST_API_LOAD", it.toString())
            }, {
                Log.d("TEST_API_LOAD", it.message.toString())
                _animeLoadErrorLiveData.value = true
            })
        compositeDisposable.add(disposable)
    }

    private fun saveAnimeListLocal(list: List<AnimeAttributes>) {
        launch {
            dao.deleteAllAnime()
            val insertRes = dao.insertAnimeList(*list.toTypedArray())
            var i = 0
            while (i < list.size) {
                list[i].id = insertRes[i].toInt()
                ++i
            }
            animeListRetrieved(list)
        }
    }

    fun loadDataFromDatabase() {

        _loadingLiveData.value = true
        launch {
            val anime = AnimeDatabase.getInstance(getApplication()).animeDao().getAllAnime()
            animeListRetrieved(anime)
        }

    }

    private fun animeListRetrieved(animeList: List<AnimeAttributes>) {
        _listOfAnimeLiveData.value = animeList
        _animeLoadErrorLiveData.value = false
        _loadingLiveData.value = false
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}