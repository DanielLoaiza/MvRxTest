package com.example.mvrx.ui.main

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized

data class TemperatureState(val temperature: Async<Int> = Uninitialized) : MvRxState