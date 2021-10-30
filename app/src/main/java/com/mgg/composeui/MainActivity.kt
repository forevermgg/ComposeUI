package com.mgg.composeui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.mgg.baselib.isMainProcess
import com.mgg.baselib.lifecycleAwareLazy
import com.mgg.composeui.ui.theme.ComposeUITheme

class MainActivity : ComponentActivity() {
    class TestLifecycleOwner : LifecycleOwner {

        private val _lifecycle = LifecycleRegistry(this)

        override fun getLifecycle(): LifecycleRegistry = _lifecycle
    }

    private var lazyProp = lifecycleAwareLazy(TestLifecycleOwner()) { "Hello World" }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeUITheme {
                Surface(color = MaterialTheme.colors.background) {
                    DefaultPreview()
                }
            }
        }
    }

    private fun startComposeTextActivity() {
        startActivity(Intent(this, ComposeTextActivity::class.java))
    }


    private fun startComposeLayoutActivity() {
        isMainProcess()
        startActivity(Intent(this, ComposeLayoutActivity::class.java))
    }

    private fun startViewModelTestActivity() {
        isMainProcess()
        startActivity(Intent(this, ViewModelTestActivity::class.java))
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ComposeUITheme {
            //Greeting("Android")
            LazyColumn(
                Modifier.padding(
                    start = 10.dp,
                    end = 10.dp,
                    top = 15.dp,
                    bottom = 15.dp
                )
            ) {
                // Add a single item
                item {
                    Button(
                        onClick = {
                            startComposeTextActivity()
                        },
                        modifier = Modifier.padding(
                            start = 10.dp,
                            end = 10.dp,
                            top = 15.dp,
                            bottom = 15.dp
                        )
                    ) {
                        Icon(
                            Icons.Filled.Favorite,
                            contentDescription = null,
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text("Compose UI Text 属性测试")
                    }
                }
                item {
                    Button(
                        onClick = {
                            startComposeLayoutActivity()
                        },
                        modifier = Modifier.padding(
                            start = 10.dp,
                            end = 10.dp,
                            top = 15.dp,
                            bottom = 15.dp
                        )
                    ) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))

                        if (lazyProp.isInitialized()) {
                            Text("Compose UI Layout 属性测试")
                        } else {
                            Text("Compose UI Layout 属性测试" + lazyProp.value)
                        }
                    }
                }

                item {
                    Button(
                        onClick = {
                            startViewModelTestActivity()
                        },
                        modifier = Modifier.padding(
                            start = 10.dp,
                            end = 10.dp,
                            top = 15.dp,
                            bottom = 15.dp
                        )
                    ) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))

                        Text("Compose UI ViewModel测试")
                    }
                }
            }
        }
    }


    @Composable
    fun CustomView() {
        val selectedItem = remember { mutableStateOf(0) }
        val context = LocalContext.current
        val customView = remember {
            android.widget.Button(context).apply {
                setOnClickListener {
                    selectedItem.value += 1
                }
            }
        }
        AndroidView({ customView }) { view ->
            view.text = selectedItem.value.toString()
        }
    }

    @Composable
    fun CustomView2() {
        val context = LocalContext.current

        val customView = remember {
            // Creates custom view
            View.inflate(context, R.layout.layout_custom_view, null)
        }
        AndroidView({ customView })
    }
}
