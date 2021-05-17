package com.example.mywiki.ui.webView

import androidx.lifecycle.MutableLiveData
import com.example.mywiki.ui.base.BaseViewModel
import com.example.mywiki.utils.common.Event
import com.example.mywiki.utils.network.NetworkHelper
import com.example.mywiki.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class WebViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {


    val launchHome: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    override fun onCreate() {
    }

    fun onBackPressed(){
        launchHome.postValue(Event(emptyMap()))
    }
}