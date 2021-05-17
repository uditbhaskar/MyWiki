package com.example.mywiki.ui.home.searchList

import androidx.appcompat.widget.DialogTitle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.mywiki.data.local.entity.SavedItemEntity
import com.example.mywiki.data.model.PagesData
import com.example.mywiki.data.repository.SaveAndFetchQueryDbRepository
import com.example.mywiki.ui.base.BaseItemViewModel
import com.example.mywiki.utils.common.Event
import com.example.mywiki.utils.network.NetworkHelper
import com.example.mywiki.utils.rx.SchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SearchItemViewModel @Inject constructor(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val saveAndFetchQueryDbRepository: SaveAndFetchQueryDbRepository

) : BaseItemViewModel<PagesData>(schedulerProvider, compositeDisposable, networkHelper) {

    val imageUrl: LiveData<String> = Transformations.map(data) { thumbNail ->
        thumbNail.thumbnail?.source
    }

    val description: LiveData<String> = Transformations.map(data) { pagesData ->
        pagesData.terms?.let {
            it.description?.get(0)
        }
    }
    val title: LiveData<String> = Transformations.map(data) { it.title }
    val pageId: LiveData<Int> = Transformations.map(data) { it.pageid }
    val onLaunchWebViewActivity: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()


    companion object {
        const val TAG = "RepoItemViewModel"
    }

    fun onLaunchWebView() {
        onLaunchWebViewActivity.postValue(Event(emptyMap()))
    }

    fun saveItemInDatabase(title: String?,description:String?,pageId:String?){
        compositeDisposable.addAll(
            Single.just(SavedItemEntity(null,title, description, pageId))
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                    {
                        saveAndFetchQueryDbRepository.saveItemsInDB(it)
                    },
                    {

                    }
                )
        )
    }

    override fun onCreate() {

    }
}