package com.mgg.composeui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mgg.composeui.ui.text.ComposeTextFragment

class ComposeTextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.compose_text_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ComposeTextFragment.newInstance())
                .commitNow()
        }
    }
}
