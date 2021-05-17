package com.example.mywiki.di.module


import android.R
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mywiki.data.repository.SaveAndFetchQueryDbRepository
import com.example.mywiki.data.repository.SearchQueryRepository
import com.example.mywiki.databinding.ActivityHomeBinding
import com.example.mywiki.databinding.ActivitySplashBinding
import com.example.mywiki.databinding.ActivityWebviewBinding
import com.example.mywiki.di.RecycleViewHorizontal
import com.example.mywiki.ui.base.BaseActivity
import com.example.mywiki.ui.home.HomeViewModel
import com.example.mywiki.ui.home.historyList.HistoryAdapter
import com.example.mywiki.ui.home.searchList.SearchAdapter
import com.example.mywiki.ui.splash.SplashViewModel
import com.example.mywiki.ui.webView.WebViewModel
import com.example.mywiki.utils.ViewModelProviderFactory
import com.example.mywiki.utils.network.NetworkHelper
import com.example.mywiki.utils.rx.SchedulerProvider

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Named


/**
 * Kotlin Generics Reference: https://kotlinlang.org/docs/reference/generics.html
 * Basically it means that we can pass any class that extends BaseActivity which take
 * BaseViewModel subclass as parameter
 */

@Module
class ActivityModule(private val activity: BaseActivity<*>) {


    @Provides
    @Named("Horizontal_layout")
    fun providesHorizontalLayout(): LinearLayoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)




    @Provides
    fun providesSplashBinding() = ActivitySplashBinding.inflate(activity.layoutInflater)

    @Provides
    fun provideSearchSuggestionArrayAdapter() =
        ArrayAdapter<String>(activity, R.layout.simple_list_item_1)

    @Provides
    fun providesSearchListAdapter() = SearchAdapter(activity.lifecycle, ArrayList())

    @Provides
    fun providesHistoryAdapter() = HistoryAdapter(activity.lifecycle, ArrayList())




    @Provides
    fun providesSplashViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): SplashViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(SplashViewModel::class) {
            SplashViewModel(
                schedulerProvider,
                compositeDisposable,
                networkHelper
            )
        }).get(SplashViewModel::class.java)

    @Provides
    fun provideHomeBinding() = ActivityHomeBinding.inflate(activity.layoutInflater)

    @Provides
    fun providesHomeViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        searchQueryRepository: SearchQueryRepository,
        saveAndFetchQueryDbRepository: SaveAndFetchQueryDbRepository
    ): HomeViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(HomeViewModel::class) {
            HomeViewModel(
                schedulerProvider,
                compositeDisposable,
                networkHelper,
                searchQueryRepository,
                saveAndFetchQueryDbRepository
            )
        }).get(HomeViewModel::class.java)

    @Provides
    fun provideWebViewBinding() = ActivityWebviewBinding.inflate(activity.layoutInflater)

    @Provides
    fun providesWebViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): WebViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(WebViewModel::class) {
            WebViewModel(
                schedulerProvider,
                compositeDisposable,
                networkHelper
            )
        }).get(WebViewModel::class.java)


}

