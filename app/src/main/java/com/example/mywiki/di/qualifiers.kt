package com.example.mywiki.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class ApplicationContext

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class ActivityContext


@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class RecycleViewHorizontal