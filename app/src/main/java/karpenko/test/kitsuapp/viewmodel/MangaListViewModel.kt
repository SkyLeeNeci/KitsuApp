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
import karpenko.test.kitsuapp.model.pojo.mangaPojo.MangaAttributes
import kotlinx.coroutines.launch

class MangaListViewModel(application: Application): BaseViewModel(application) {

    val dao = AnimeDatabase.getInstance(application).mangaDao()
    private val compositeDisposable = CompositeDisposable()

    private val _listOfMangaLiveData = MutableLiveData<List<MangaAttributes>>()
    val listOfMangaLiveData: LiveData<List<MangaAttributes>>
        get() = _listOfMangaLiveData

    private val _mangaLoadErrorLiveData = MutableLiveData<Boolean>()
    val mangaLoadErrorLiveData: LiveData<Boolean>
        get() = _mangaLoadErrorLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData



    fun loadDataFromRemote(){
        val disposable = AnimeApiFactory.mangaApiService.getMangaList()
            .map { it.data.map { it.mangaAttributes}}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                       saveMangaLocal(it)
                Log.d("TEST_MANGA_LOAD", it.toString())
            },{
                Log.d("TEST_MANGA_LOAD", it.message.toString())
                _mangaLoadErrorLiveData.value = true
            })
        compositeDisposable.add(disposable)
    }


    private fun saveMangaLocal(list: List<MangaAttributes>){
        launch{
            dao.deleteAllManga()
            val insertRes = dao.insertAnimeList(*list.toTypedArray())
            var i = 0
            while (i< list.size){
                list[i].id = insertRes[i].toInt()
                ++i
            }
            mangaListRetrieved(list)
        }
    }

    fun loadDataFromDatabase(){
        _loadingLiveData.value = true
        launch {
            val manga = AnimeDatabase.getInstance(getApplication()).mangaDao().getAllManga()
            mangaListRetrieved(manga)
        }

    }

    private fun mangaListRetrieved(animeList: List<MangaAttributes>){
        _listOfMangaLiveData.value = animeList
        _mangaLoadErrorLiveData.value = false
        _loadingLiveData.value = false
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}