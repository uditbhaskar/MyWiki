package com.example.mywiki.ui.home.historyList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.mywiki.data.local.entity.SavedItemEntity
import com.example.mywiki.data.repository.SaveAndFetchQueryDbRepository
import com.example.mywiki.ui.base.BaseItemViewModel
import com.example.mywiki.utils.common.Event
import com.example.mywiki.utils.network.NetworkHelper
import com.example.mywiki.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HistoryItemViewModel @Inject constructor(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val saveAndFetchQueryDbRepository: SaveAndFetchQueryDbRepository

) : BaseItemViewModel<SavedItemEntity>(schedulerProvider, compositeDisposable, networkHelper) {


    val description: LiveData<String> = Transformations.map(data) {it.description}
    val title: LiveData<String> = Transformations.map(data) { it.title }
    val pageId: LiveData<String> = Transformations.map(data) { it.pageId }
    val onLaunchWebViewActivity: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    override fun onCreate() {

    }

    fun onLaunchWebView() {
        onLaunchWebViewActivity.postValue(Event(emptyMap()))
    }

}