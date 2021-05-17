package com.example.mywiki.ui.splash

import androidx.lifecycle.MutableLiveData
import com.example.mywiki.ui.base.BaseViewModel
import com.example.mywiki.utils.common.Event
import com.example.mywiki.utils.network.NetworkHelper
import com.example.mywiki.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class SplashViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val launchHome = MutableLiveData<Event<Boolean>>()

    override fun onCreate() {
        launchHome.postValue(Event(true))
    }
}