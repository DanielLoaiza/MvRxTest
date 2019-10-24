package com.example.mvrx.ui.main

class MainViewModel(initialState: HelloWorldState) : MvRxViewModel<HelloWorldState>(initialState) {
   fun incrementCount() = setState { copy( count = count + 1) }
}
