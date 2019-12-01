package ru.kpfu.itis.android.animationsapp.ui.details

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import kotlinx.android.synthetic.main.fragment_details_main.*
import ru.kpfu.itis.android.animationsapp.App
import ru.kpfu.itis.android.animationsapp.R
import ru.kpfu.itis.android.animationsapp.di.list.component.DaggerListComponent
import ru.kpfu.itis.android.animationsapp.di.list.module.ListModule
import ru.kpfu.itis.android.animationsapp.di.list.module.PresenterModule
import ru.kpfu.itis.android.animationsapp.model.Item

class DetailsFragment : MvpAppCompatFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.move).apply {
                duration = DURATION
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_details_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTransitionName()
    }

    private fun setTransitionName() {
        tv_title_item.transitionName = arguments?.getString(ARG_TEXT)
        iv_item.transitionName = arguments?.getString(ARG_IMAGE)
        arguments?.getParcelable<Item>(ARG_ITEM)?.also {
            iv_item.setImageResource(it.imageId)
            tv_title_item.text = it.title
        }
    }

    companion object {

        private const val DURATION = 500L
        private const val ARG_ITEM = "ARG_ITEM"
        private const val ARG_TEXT = "ARG_TEXT"
        private const val ARG_IMAGE = "ARG_IMAGE"

        fun newInstance(item: Item, textName: String, imageName: String): DetailsFragment =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_ITEM, item)
                    putString(ARG_TEXT, textName)
                    putString(ARG_IMAGE, imageName)
                }
            }
    }
}
