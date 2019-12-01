package ru.kpfu.itis.android.animationsapp.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list_main.*
import ru.kpfu.itis.android.animationsapp.R
import ru.kpfu.itis.android.animationsapp.model.Item
import ru.kpfu.itis.android.animationsapp.utils.AnimInfo

class Adapter(
    private val sourceLambda: (Item, AnimInfo) -> Unit
) : ListAdapter<Item, Adapter.ItemHolder>(
    ItemDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_main, parent, false)
        return ItemHolder(view, sourceLambda)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem == newItem
    }

    class ItemHolder(
        override val containerView: View,
        private val clickListener: (Item, AnimInfo) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: Item) {
            tv_title_item.text = item.title
            iv_item.setImageResource(item.imageId)

            val textName = "tv_transition_$adapterPosition"
            val imageName = "iv_transition_$adapterPosition"
            tv_title_item.transitionName = textName
            iv_item.transitionName = imageName

            val info = AnimInfo(
                adapterPosition,
                imageName,
                textName
            )
            itemView.setOnClickListener { clickListener(item, info) }
        }
    }
}
