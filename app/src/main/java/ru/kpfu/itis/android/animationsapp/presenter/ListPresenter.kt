package ru.kpfu.itis.android.animationsapp.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.kpfu.itis.android.animationsapp.model.Item
import ru.kpfu.itis.android.animationsapp.ui.list.ListView
import ru.kpfu.itis.android.animationsapp.utils.AnimInfo
import ru.kpfu.itis.android.animationsapp.utils.ListCreator

@InjectViewState
class ListPresenter(
    private val listCreator: ListCreator
) : MvpPresenter<ListView>() {

    fun getList() {
        viewState.displayList(listCreator.getCreatedList())
    }

    fun itemClick(item: Item, animInfo: AnimInfo) {
        viewState.navigateToSecond(item, animInfo)
    }
}
