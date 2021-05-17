package com.example.mywiki.ui.home.searchList

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.example.mywiki.data.model.PagesData
import com.example.mywiki.ui.base.BaseAdapter

class SearchAdapter(
    parentLifecycle : Lifecycle,
    searchItems: ArrayList<PagesData>

) : BaseAdapter<PagesData, SearchItemViewHolder>(parentLifecycle,searchItems){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder =SearchItemViewHolder(parent)
}
