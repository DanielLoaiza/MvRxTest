package com.example.mvrx.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.*
import com.example.mvrx.R
import kotlinx.android.synthetic.main.main_fragment.*

class TemperatureFragment : BaseMvRxFragment() {

    companion object {
        fun newInstance() = TemperatureFragment()
    }

    private val viewModel: TemperatureViewModel by fragmentViewModel()

    private lateinit var listener: FragmentChangeListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.subscribe {
            Log.d("Test", "te state is $it")
        }

        viewModel.selectSubscribe(TemperatureState::temperature, deliveryMode = UniqueOnly("id")) { temperature ->
            Log.d("Test", "The temperature is $temperature")
        }

        viewModel.asyncSubscribe(TemperatureState::temperature, onSuccess =  { temperature ->
            Log.d("Test", "The temperature is $temperature")
        }, onFail = { error ->
            Log.e("Test", "The temperature failed with" , error)
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as FragmentChangeListener

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        message.setOnClickListener {
            viewModel.fetchTemperature()
        }
        button.setOnClickListener {
            listener.onFragmentChange("")
        }
    }

    override fun invalidate() = withState(viewModel) { state ->
        message.text = when(state.temperature) {
            is Uninitialized -> "Click to load temperature"
            is Loading -> "Temperature is loading"
            is Success -> "Weather: ${state.temperature()}"
            is Fail -> "Failed fetching"
        }
    }
}
