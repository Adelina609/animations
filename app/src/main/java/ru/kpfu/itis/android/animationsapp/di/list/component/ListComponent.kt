package ru.kpfu.itis.android.animationsapp.di.list.component

import dagger.Component
import ru.kpfu.itis.android.animationsapp.MainActivity
import ru.kpfu.itis.android.animationsapp.di.app.component.AppComponent
import ru.kpfu.itis.android.animationsapp.di.list.module.ListModule
import ru.kpfu.itis.android.animationsapp.di.list.module.PresenterModule
import ru.kpfu.itis.android.animationsapp.di.list.scope.ListScope
import ru.kpfu.itis.android.animationsapp.ui.details.DetailsFragment
import ru.kpfu.itis.android.animationsapp.ui.list.ListFragment

@ListScope
@Component(
    dependencies = [AppComponent::class],
    modules = [PresenterModule::class, ListModule::class]
)
interface ListComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(listFragment: ListFragment)
}
