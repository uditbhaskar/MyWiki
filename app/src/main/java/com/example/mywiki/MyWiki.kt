package com.example.mywiki

import android.app.Application
import com.example.mywiki.di.component.ApplicationComponent
import com.example.mywiki.di.component.DaggerApplicationComponent
import com.example.mywiki.di.module.ApplicationModule

class MyWiki : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}