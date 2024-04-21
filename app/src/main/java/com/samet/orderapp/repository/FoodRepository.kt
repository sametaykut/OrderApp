package com.samet.orderapp.repository

import com.samet.orderapp.retrofit.RetrofitInstance

class FoodRepository() {

    suspend fun getFoods() = RetrofitInstance.api.getFoods()

}