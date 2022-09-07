package com.example.mywiki.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.mywiki.data.local.entity.SavedItemEntity
import com.example.mywiki.data.model.PagesData
import com.example.mywiki.data.repository.SaveAndFetchQueryDbRepository
import com.example.mywiki.data.repository.SearchQueryRepository
import com.example.mywiki.ui.base.BaseViewModel
import com.example.mywiki.utils.common.Resource
import com.example.mywiki.utils.network.NetworkHelper
import com.example.mywiki.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class HomeViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val searchQueryRepository: SearchQueryRepository,
    private val saveAndFetchQueryDbRepository: SaveAndFetchQueryDbRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val searchFieldSuggestion: MutableLiveData<List<String>> = MutableLiveData()
    val searchQueryData: MutableLiveData<Resource<List<PagesData>>> = MutableLiveData()
    val savedHistoryData: MutableLiveData<Resource<List<SavedItemEntity>>> = MutableLiveData()


    override fun onCreate() {
        onFetchingHistoryData()
    }


    fun onFetchingHistoryData() {
        compositeDisposable.add(
            saveAndFetchQueryDbRepository.fetchItemsFromDB()
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                    {
                        savedHistoryData.postValue(Resource.success(it))
                    },
                    {

                    }
                )
        )

    }

    fun onSearching(q: String) {
        if (checkInternetConnectionWithMessage()) {
            compositeDisposable.add(
                searchQueryRepository.fetchSearchQuery(q)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            searchQueryData.postValue(Resource.success(it))
                        },
                        {
                            searchQueryData.postValue(Resource.error(emptyList()))
                        }
                    )
            )
        }

    }

    fun onSearchingSuggestion(q: String) {
        if (checkInternetConnectionWithMessage()) {
            compositeDisposable.add(
                searchQueryRepository.fetchSearchSuggestion(q)
                    .subscribeOn(schedulerProvider.io())
                    .debounce(200, TimeUnit.MILLISECONDS)  //removing unnecessary network calls
                    .filter {
                        return@filter it.isNotEmpty()           //filtering the search with no result calls
                    }
                    .subscribe(
                        { it ->
                            val z = arrayListOf<String>()
                            it.forEach {
                                it.title?.let { it1 -> z.add(it1) }
                            }
                            searchFieldSuggestion.postValue(z)
                        }, {

                        }
                    )
            )


        }
    }

}