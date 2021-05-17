package com.example.mywiki.di.component

import com.example.mywiki.di.ViewModelScope
import com.example.mywiki.di.module.ViewHolderModule
import com.example.mywiki.ui.home.historyList.HistoryItemViewHolder
import com.example.mywiki.ui.home.historyList.HistoryItemViewModel
import com.example.mywiki.ui.home.searchList.SearchItemViewHolder

import dagger.Component

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {
    fun inject(itemViewHolder: SearchItemViewHolder)
    fun inject(itemViewHolder: HistoryItemViewHolder)
}