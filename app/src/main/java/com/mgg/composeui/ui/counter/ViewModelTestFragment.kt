package com.mgg.composeui.ui.counter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mgg.composeui.R
import kotlinx.android.synthetic.main.view_model_test_fragment.*

class ViewModelTestFragment : Fragment() {

    companion object {
        fun newInstance() = ViewModelTestFragment()
    }

    private lateinit var viewModel: ViewModelTestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(R.layout.view_model_test_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewModelTestViewModel::class.java)
        viewModel.count.observe(viewLifecycleOwner, {
            message.text = it.toString()
        })

        messageBtn.setOnClickListener {
            val data = viewModel.count.value?.plus(1)
            if (data != null) {
                viewModel.onCountChanged(data)
            }
        }
    }

}