package com.example.mynewapplication.kotlinpackage

import android.app.Application
import com.android.volley.BuildConfig
import com.example.mynewapplication.kotlinpackage.core.di.*
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

class RecipePuppyApp : Application() {

    companion object {
        var appContext: RecipePuppyApp? = null
        var BASE_URL = "http://www.recipepuppy.com/"
    }

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .databaseModule(DatabaseModule(this))
            .networkModule(NetworkModule(BASE_URL))
            .dataModule(DataModule())
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        this.injectMembers()
        this.initializeLeakDetection()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun injectMembers() = appComponent.inject(this)

    private fun initializeLeakDetection() {
        if (BuildConfig.DEBUG) LeakCanary.install(this)
    }
}