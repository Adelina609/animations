package ru.kpfu.itis.android.animationsapp.di.app.component

import android.content.Context
import dagger.Component
import ru.kpfu.itis.android.animationsapp.di.app.module.ContextModule
import ru.kpfu.itis.android.animationsapp.di.app.scope.ApplicationScope

@ApplicationScope
@Component(
    modules = [ContextModule::class]
)
interface AppComponent {
    fun provideApp(): Context
}
