package com.samet.orderapp.model

data class FoodResponse(
    val success: Int,
    val yemekler: List<Yemekler>
)