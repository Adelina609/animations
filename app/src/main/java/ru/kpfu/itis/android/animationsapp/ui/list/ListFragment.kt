package ru.kpfu.itis.android.animationsapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
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
    private var isAnimatingAvailable = true

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

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    isAnimatingAvailable = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && isAnimatingAvailable) {
                    animate(0f, 1f)
                } else if (dy < 0 && isAnimatingAvailable) {
                    animate(1f, 0f)
                }
            }
        })
        adapter = Adapter { item, info ->
            listPresenter.itemClick(item, info)
        }
        rv_main.adapter = adapter
    }

    private fun animate(alphaFrom: Float, alphaTo: Float) {
        tv_title_list.apply {
            alpha = alphaFrom
            animate().alpha(alphaTo).setListener(null).duration =
                resources.getInteger(R.integer.fade_anim_duration).toLong()
        }
        isAnimatingAvailable = false
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
