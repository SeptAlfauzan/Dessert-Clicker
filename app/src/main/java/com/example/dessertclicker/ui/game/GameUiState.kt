package com.example.dessertclicker.ui.game

import com.example.dessertclicker.data.DataSource
import com.example.dessertclicker.model.Dessert

data class GameUiState (
    val revenue: Int = 0,
    val dessertSold: Int = 0,
    val currentDessertIndex: Int = 0,
    val currentDessert: Dessert = DataSource.dessertList[currentDessertIndex],
    val currentDessertImageId: Int = currentDessert.imageId,
    val currentDessertPrice: Int = currentDessert.price,
    val isGameOver: Boolean = false
)