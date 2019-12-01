package ru.kpfu.itis.android.animationsapp.di.list.module

import dagger.Module
import dagger.Provides
import ru.kpfu.itis.android.animationsapp.di.list.scope.ListScope
import ru.kpfu.itis.android.animationsapp.presenter.ListPresenter
import ru.kpfu.itis.android.animationsapp.utils.ListCreator

@Module
class PresenterModule {
    @Provides
    @ListScope
    fun provideListPresenter(
        listCreator: ListCreator
    ): ListPresenter =
        ListPresenter(listCreator)
}
