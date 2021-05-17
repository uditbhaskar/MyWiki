package com.example.mywiki.di.component

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.mywiki.MyWiki
import com.example.mywiki.data.local.DatabaseService
import com.example.mywiki.data.remote.NetworkService
import com.example.mywiki.data.repository.SaveAndFetchQueryDbRepository
import com.example.mywiki.data.repository.SearchQueryRepository
import com.example.mywiki.di.ApplicationContext
import com.example.mywiki.di.module.ApplicationModule
import com.example.mywiki.utils.network.NetworkHelper
import com.example.mywiki.utils.rx.SchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: MyWiki)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context

    /**
     * These methods are written in ApplicationComponent because the instance of
     * NetworkService is singleton and is maintained in the ApplicationComponent's implementation by Dagger
     * For NetworkService singleton instance to be accessible to say DummyActivity's DummyViewModel
     * this ApplicationComponent must expose a method that returns NetworkService instance
     * This method will be called when NetworkService is injected in DummyViewModel.
     * Also, in ActivityComponent you can find dependencies = [ApplicationComponent::class] to link this relationship
     */

    fun getNetworkService(): NetworkService

    fun getDatabaseService(): DatabaseService

    fun getSharedPreferences(): SharedPreferences

    fun getNetworkHelper(): NetworkHelper

    /**---------------------------------------------------------------------------
     * Dagger will internally create UserRepository instance using constructor injection.
     * Dependency through constructor
     * UserRepository ->
     *  [NetworkService -> Nothing is required],
     *  [DatabaseService -> Nothing is required],
     *  [UserPreferences -> [SharedPreferences -> provided by the function provideSharedPreferences in ApplicationModule class]]
     * So, Dagger will be able to create an instance of UserRepository by its own using constructor injection
     *---------------------------------------------------------------------------------
     */

    fun getSchedulerProvider(): SchedulerProvider

    fun getSearchQueryRepo() : SearchQueryRepository


    fun getCompositeDisposable(): CompositeDisposable
}