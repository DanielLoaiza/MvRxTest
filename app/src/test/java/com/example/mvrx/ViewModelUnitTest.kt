package com.example.mvrx

import com.airbnb.mvrx.test.MvRxTestRule
import com.airbnb.mvrx.withState
import com.example.mvrx.ui.main.MvRxViewModel
import org.junit.Assert.assertEquals
import org.junit.ClassRule
import org.junit.Test

class FoodViewModel(initialState: FoodState): MvRxViewModel<FoodState>(initialState) {

    fun addFood(food: Food) = setState { copy(foods = foods + food) }
}

class ViewModelUnitTest {

//    companion object {
//        @JvmField
//        @ClassRule
//        val mvrxRule = MvRxTestRule()
//    }

    @Test
    fun test_initial_state() {
        val initialState  = FoodState(setOf(Food("Salmon", isVegetarian = false, isGlutenFree = true)))
        val viewModel = FoodViewModel(initialState)
        withState(viewModel) { state ->
            assertEquals(initialState, state)
        }
    }

    @Test
    fun test_add_food() {
        val salmon = Food("Salmon", isVegetarian = false, isGlutenFree = true)
        val initialState  = FoodState(setOf(salmon))
        val viewModel = FoodViewModel(initialState)
        val pepperoniPizza = Food("Pepperoni Pizza", isVegetarian = false, isGlutenFree = false)
        viewModel.addFood(pepperoniPizza)
        withState(viewModel) { state ->
            assertEquals(setOf(salmon, pepperoniPizza), state.foods)
        }
    }
}