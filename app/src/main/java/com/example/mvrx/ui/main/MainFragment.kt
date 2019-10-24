package com.example.mvrx.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.example.mvrx.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : BaseMvRxFragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var listener: FragmentChangeListener

    private val viewModel: MainViewModel by fragmentViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as FragmentChangeListener

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        message.setOnClickListener {
            viewModel.incrementCount()
        }

        button.setOnClickListener {
            listener.onFragmentChange("temperature")
        }
    }

    override fun invalidate() = withState(viewModel) { state ->
        message.text = state.titleWithCount
    }
}
