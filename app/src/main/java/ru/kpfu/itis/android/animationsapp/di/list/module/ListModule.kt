package ru.kpfu.itis.android.animationsapp.di.list.module

import dagger.Module
import dagger.Provides
import ru.kpfu.itis.android.animationsapp.di.list.scope.ListScope
import ru.kpfu.itis.android.animationsapp.utils.ListCreator

@Module
class ListModule {

    @Provides
    @ListScope
    fun provideListCreator(): ListCreator = ListCreator()
}
