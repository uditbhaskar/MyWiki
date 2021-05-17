package com.example.mywiki.di.component

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mywiki.di.ActivityScope
import com.example.mywiki.di.RecycleViewHorizontal
import com.example.mywiki.di.module.ActivityModule
import com.example.mywiki.ui.home.HomeActivity
import com.example.mywiki.ui.splash.SplashActivity
import com.example.mywiki.ui.webView.WebViewActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity:SplashActivity)

    fun inject(activity: HomeActivity)

    fun inject(activity: WebViewActivity)


}