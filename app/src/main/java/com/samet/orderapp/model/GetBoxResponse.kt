package com.samet.orderapp.model

data class GetBoxResponse(
    val sepet_yemekler: List<SepetYemekler>,
    val success: Int
)