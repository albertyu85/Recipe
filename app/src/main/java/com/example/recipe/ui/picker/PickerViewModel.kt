package com.example.recipe.ui.picker

import androidx.lifecycle.ViewModel

class PickerViewModel : ViewModel() {
    val cuisinesData = listOf("African", "American", "British", "Cajun", "Caribbean", "Chinese", "Eastern European",
            "European", "French", "German", "Greek", "Indian", "Irish", "Italian", "Japanese", "Jewish", "Korean", "Latin American",
            "Mediterranean", "Mexican", "Middle Eastern", "Nordic", "Southern", "Spanish", "Thai", "Vietnamese")

    val dietsData = listOf("Gluten Free", "Ketogenic", "Vegetarian", "Lacto-Vegetarian", "Oro-Vegetarian", "Vegan", "Pescetarian",
            "Paleo", "Whole 30")

    val mealTypeData = listOf("Main Course", "Side Dish", " Dessert", " Appetizer", "Salad", "Bread", "Breakfast", "Soup", "Beverage",
            "Sauce", "Marinade", "Finger Food", "Snack", "Drink")

    val filterData = listOf("Popular", "Random", "Healthy", "Cheap", "Easy", "Fast")

}
