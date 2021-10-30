package com.mgg.composeui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mgg.composeui.ui.layout.ComposeLayoutFragment

class ComposeLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.compose_layout_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ComposeLayoutFragment.newInstance())
                .commitNow()
        }
    }
}
