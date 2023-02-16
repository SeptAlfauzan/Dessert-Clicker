package com.example.dessertclicker.model

import androidx.annotation.DrawableRes

data class Dessert(
    @DrawableRes val imageId: Int,
    val price: Int,
    val startProductionAmout: Int
)
