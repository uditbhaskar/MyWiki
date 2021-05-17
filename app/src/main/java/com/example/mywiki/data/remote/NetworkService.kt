package com.example.mywiki.data.remote

import com.example.mywiki.data.remote.response.SearchResponse
import io.reactivex.Single
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET(Endpoints.SEARCH)
    fun doSearchQuery(
        @Query("gpssearch") searchQuery :String,
        @Query("action") action:String,
        @Query("formatversion") formatversion:String,
        @Query("generator") generator:String,
        @Query("gpslimit") gpslimit:String,
        @Query("prop") prop:String,
        @Query("piprop") piprop:String,
        @Query("pithumbsize") pithumbsize:String,
        @Query("pilimit") pilimit:String,
        @Query("wbptterms") wbptterms:String,
        @Query("format") format:String
    ):Single<SearchResponse>

    @GET(Endpoints.SEARCH_SUGGESTION)
    fun fetchSearchSuggestion(
        @Query("gpssearch") gpssearch:String,
        @Query("action") action:String,
        @Query("formatversion") formatversion:String,
        @Query("format") format:String,
        @Query("generator") generator:String,
        @Query("gpslimit") gpslimit:String
    ):Single<SearchResponse>

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

//api.php?action=query&formatversion=2&generator=prefixsearch&gpssearch=lolkni&gpslimit=10&prop=pageimages|pageterms&piprop=thumbnail&pithumbsize=50&pilimit=10&wbptterms=description&format=jsonfm