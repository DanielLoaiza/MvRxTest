package com.example.mvrx

import com.airbnb.mvrx.MvRxState
import org.junit.Test

import org.junit.Assert.*

data class Food(val name: String, val isVegetarian: Boolean, val isGlutenFree: Boolean)

data class FoodState(val foods: Set<Food> = emptySet()): MvRxState {
    val vegetarianFoods = foods.filter(Food::isVegetarian).toSet()
    val glutenFreeFoods = foods.filter(Food::isGlutenFree).toSet()

    fun containsFood(name:String) = foods.any { it.name.equals(name, ignoreCase = true) }

}

class StateUnitTest {
    @Test
    fun test_no_foods() {
        val state = FoodState()
        assertEquals(emptySet<Food>(), state.vegetarianFoods)
        assertEquals(emptySet<Food>(), state.glutenFreeFoods)
    }

    @Test
    fun test_foods() {
        val pepperoniPizza = Food("Pepperoni Pizza", isVegetarian = false, isGlutenFree = false)
        val cauliflowerPizza = Food("Cauliflower Pizza", isVegetarian = true, isGlutenFree = true)
        val salmon = Food("Salmon", isVegetarian = false, isGlutenFree = true)

        val state = FoodState(setOf(pepperoniPizza, cauliflowerPizza, salmon))
        assertEquals(setOf(cauliflowerPizza), state.vegetarianFoods)
        assertEquals(setOf(cauliflowerPizza, salmon), state.glutenFreeFoods)
    }

    @Test
    fun test_contains_food() {
        val salmon = Food("Salmon", isVegetarian = false, isGlutenFree = true)

        val state = FoodState(setOf(salmon))
        assertTrue(state.containsFood("salmon"))
        assertFalse(state.containsFood("pepperoni pizza"))
    }
}
