package ru.kpfu.itis.android.animationsapp

import android.os.Bundle
import ru.kpfu.itis.android.animationsapp.ui.list.ListFragment
import com.arellomobile.mvp.MvpAppCompatActivity

class MainActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment()
    }

    private fun addFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container_main, ListFragment())
            .commit()
    }
}
