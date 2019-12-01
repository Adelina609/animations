package ru.kpfu.itis.android.animationsapp.utils

import ru.kpfu.itis.android.animationsapp.R
import ru.kpfu.itis.android.animationsapp.model.Item

class ListCreator {
    fun getCreatedList(): List<Item> {
        val item = Item("duck", R.drawable.ic_duck)
        val item1 = Item("falcon", R.drawable.ic_falcon)
        val item2 = Item("pelican", R.drawable.ic_pelican)
        val item3 = Item("penguin", R.drawable.ic_penguin)
        return listOf(item, item1, item2, item3, item, item1,item2, item3,item, item1, item2, item3,item, item1, item2, item3)
    }
}
