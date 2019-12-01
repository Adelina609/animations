package ru.kpfu.itis.android.animationsapp

import android.app.Application
import ru.kpfu.itis.android.animationsapp.di.app.component.AppComponent
import ru.kpfu.itis.android.animationsapp.di.app.component.DaggerAppComponent
import ru.kpfu.itis.android.animationsapp.di.app.module.ContextModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }

    companion object {
        private var appComponent: AppComponent? = null

        fun getAppComponents(): AppComponent? = appComponent
    }
}
