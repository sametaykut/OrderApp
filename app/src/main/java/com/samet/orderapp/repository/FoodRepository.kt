package com.samet.orderapp.repository

import com.samet.orderapp.model.GetBoxResponse
import com.samet.orderapp.retrofit.ProductAPI
import com.samet.orderapp.retrofit.RetrofitInstance
import retrofit2.Response
import retrofit2.http.Field
import javax.inject.Inject

class FoodRepository @Inject constructor(val productAPI: ProductAPI) {

    suspend fun getFoods() = productAPI.getFoods()
    suspend fun addToBox(yemek_adi:String,
                         yemek_resim_adi:String,
                         yemek_fiyat:Int,
                         yemek_siparis_adet:Int,
                         kullanici_adi:String) = productAPI.addToBox(yemek_adi,yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)

    suspend fun getBox(kullanici_adi: String)= productAPI.getBox(kullanici_adi)

    suspend fun deleteBox(sepet_yemek_id:Int,kullanici_adi:String) = productAPI.deleteBox(sepet_yemek_id,kullanici_adi)

}