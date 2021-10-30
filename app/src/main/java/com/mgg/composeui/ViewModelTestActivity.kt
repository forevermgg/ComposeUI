package com.mgg.composeui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mgg.composeui.ui.counter.ViewModelTestFragment

class ViewModelTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_model_test_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ViewModelTestFragment.newInstance())
                .commitNow()
        }
    }
}