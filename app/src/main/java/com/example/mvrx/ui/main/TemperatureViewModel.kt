package com.example.mvrx.ui.main

import io.reactivex.Observable
import java.util.concurrent.TimeUnit


class TemperatureViewModel(temperatureState: TemperatureState) :
    MvRxViewModel<TemperatureState>(temperatureState) {

    fun fetchTemperature() {
        Observable.just(72)
            .delay(2, TimeUnit.SECONDS)
            .execute { copy(temperature = it) }
    }
}