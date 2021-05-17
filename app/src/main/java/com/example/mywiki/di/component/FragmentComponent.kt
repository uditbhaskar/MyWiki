package com.example.mywiki.di.component

import com.example.mywiki.di.FragmentScope
import com.example.mywiki.di.module.FragmentModule
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {


}