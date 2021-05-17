package com.example.mywiki.data.repository

import com.example.mywiki.data.local.DatabaseService
import com.example.mywiki.data.model.PagesData
import com.example.mywiki.data.remote.NetworkService
import com.example.mywiki.data.remote.response.SearchResponse
import io.reactivex.Single
import javax.inject.Inject

class SearchQueryRepository @Inject constructor(
    private val networkService: NetworkService
) {

    fun fetchSearchQuery(searchQuery: String): Single<List<PagesData>> =
        networkService.doSearchQuery(
            searchQuery,
            "query",
            "2",
            "prefixsearch",
            "10",
            "pageimages|pageterms",
            "thumbnail",
            "50",
            "10",
            "description",
            "json"
        ).map {
            it.query.run {
                it.query?.pages
            }
        }

    fun fetchSearchSuggestion(searchSuggestionQuery:String): Single<List<PagesData>> =
        networkService.fetchSearchSuggestion(
            searchSuggestionQuery,
            "query",
            "2",
            "json",
            "prefixsearch",
            "10"
        ).map {
            it.query.run {
                it.query?.pages
            }
        }

}

// &gpssearch=aa&
// action=query
// &formatversion=2
// format=json
// &generator=prefixsearch




// &gpsearch=Albert Einstein
// action=query
// &formatversion=2
// &generator=prefixsearch
// &gpslimit=10
// &prop=pageimages|pageterms
// &piprop=thumbnail
// &pithumbsize=50
// &pilimit=10
// &wbptterms=description
// &format=json
