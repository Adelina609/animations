package ru.kpfu.itis.android.animationsapp.ui.list

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.kpfu.itis.android.animationsapp.model.Item
import ru.kpfu.itis.android.animationsapp.utils.AnimInfo

@StateStrategyType(OneExecutionStateStrategy::class)
interface ListView : MvpView {
    fun displayList(list: List<Item>)
    fun navigateToSecond(item: Item, animInfo: AnimInfo)
}
