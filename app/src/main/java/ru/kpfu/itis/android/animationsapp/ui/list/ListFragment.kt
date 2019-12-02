package ru.kpfu.itis.android.animationsapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.IdRes
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_list_main.*
import ru.kpfu.itis.android.animationsapp.App
import ru.kpfu.itis.android.animationsapp.R
import ru.kpfu.itis.android.animationsapp.di.list.component.DaggerListComponent
import ru.kpfu.itis.android.animationsapp.di.list.module.ListModule
import ru.kpfu.itis.android.animationsapp.di.list.module.PresenterModule
import ru.kpfu.itis.android.animationsapp.model.Item
import ru.kpfu.itis.android.animationsapp.presenter.ListPresenter
import ru.kpfu.itis.android.animationsapp.ui.details.DetailsFragment
import ru.kpfu.itis.android.animationsapp.utils.AnimInfo
import javax.inject.Inject

class ListFragment : MvpAppCompatFragment(), ListView {

    @Inject
    @InjectPresenter
    lateinit var listPresenter: ListPresenter

    @ProvidePresenter
    fun getPresenter(): ListPresenter = listPresenter

    private var adapter: Adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerListComponent.builder()
            .appComponent(App.getAppComponents())
            .presenterModule(PresenterModule())
            .listModule(ListModule())
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_list_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        listPresenter.getList()
    }

    private fun initAdapter() {
        rv_main.layoutManager = LinearLayoutManager(context)
        rv_main.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    tv_title_list.startAnimation(AnimationUtils
                        .loadAnimation(context, R.anim.fade_in))

                }
                if (dy < 0) {
                    tv_title_list.startAnimation(AnimationUtils
                        .loadAnimation(context, R.anim.fade_out))
                }
            }
        })
        adapter = Adapter { item, info ->
            listPresenter.itemClick(item, info)
        }
        rv_main.adapter = adapter
    }

    override fun displayList(list: List<Item>) {
        adapter?.submitList(list)
    }

    override fun navigateToSecond(item: Item, animInfo: AnimInfo) {
        val textView = getViewByPosition(animInfo.position, R.id.tv_title_item)
        val imageView = getViewByPosition(animInfo.position, R.id.iv_item)
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            imageView?.let {
                addSharedElement(it, animInfo.imageName)
            }
            textView?.let {
                addSharedElement(it, animInfo.textName)
            }
            replace(
                R.id.container_main,
                DetailsFragment.newInstance(item, animInfo.textName, animInfo.imageName)
            )
            addToBackStack(null)
            commit()
        }
    }

    private fun getViewByPosition(pos: Int, @IdRes viewId: Int): View? =
        rv_main.findViewHolderForAdapterPosition(pos)?.itemView?.findViewById(viewId)
}
