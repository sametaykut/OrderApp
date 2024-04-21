package com.samet.orderapp.retrofit

import com.samet.orderapp.model.FoodResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductAPI {

    @GET("yemekler/tumYemekleriGetir.php ")
    suspend fun getFoods():Response<FoodResponse>


}