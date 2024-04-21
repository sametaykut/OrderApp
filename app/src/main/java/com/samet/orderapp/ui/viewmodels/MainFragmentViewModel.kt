package com.samet.orderapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samet.orderapp.model.FoodResponse
import com.samet.orderapp.repository.FoodRepository
import kotlinx.coroutines.launch

class MainFragmentViewModel() : ViewModel() {

    val repository= FoodRepository()
    val foodList = MutableLiveData<FoodResponse>()

    init {
        getFoods()
    }
    fun getFoods(){
        viewModelScope.launch {
            val response = repository.getFoods()
            if (response.isSuccessful){
                foodList.value= response.body()
            }
        }
    }



}