package com.samet.orderapp.model

import java.io.Serializable

data class SepetYemekler(
    val kullanici_adi: String,
    val sepet_yemek_id: Int,
    val yemek_siparis_adet: String,
    val yemek_adi: String,
    val yemek_fiyat: String,
    val yemek_resim_adi: String
)