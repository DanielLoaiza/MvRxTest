package com.example.mvrx.ui.main

import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.PersistState

data class HelloWorldState(
    val title: String = "Hello world",
    @PersistState val count: Int = 0
): MvRxState {
    val titleWithCount = "$title - $count"
}