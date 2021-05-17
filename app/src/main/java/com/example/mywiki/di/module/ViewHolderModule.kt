package com.example.mywiki.di.module

import androidx.lifecycle.LifecycleRegistry
import com.example.mywiki.di.ViewModelScope
import com.example.mywiki.ui.base.BaseItemViewHolder

import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*, *>) {

    @Provides
    @ViewModelScope
    fun provideLifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)

}