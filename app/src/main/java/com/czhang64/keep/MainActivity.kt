package com.czhang64.keep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.czhang64.keep.ui.SplashFragment
import com.czhang64.keep.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, SplashFragment.newInstance())
                    .commitNow()
        }
    }
}