package com.example.mywiki.ui.home.historyList

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.example.mywiki.data.local.entity.SavedItemEntity
import com.example.mywiki.ui.base.BaseAdapter

class HistoryAdapter(
    parentLifecycle: Lifecycle,
    savedItem: ArrayList<SavedItemEntity>
) : BaseAdapter<SavedItemEntity, HistoryItemViewHolder>(parentLifecycle, savedItem) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItemViewHolder =
        HistoryItemViewHolder(parent)
}