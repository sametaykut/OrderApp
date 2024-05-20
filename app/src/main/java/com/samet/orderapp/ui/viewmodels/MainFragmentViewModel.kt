package com.samet.orderapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samet.orderapp.model.FoodResponse
import com.samet.orderapp.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(val repository: FoodRepository) : ViewModel() {

    val foodList = MutableLiveData<FoodResponse>()
    val isLoading = MutableLiveData(false)
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    init {
        getFoods()
    }
    fun getFoods() {
        isLoading.value = true

        viewModelScope.launch {
            try {
                val response = repository.getFoods()
                if (response.isSuccessful) {
                    foodList.postValue(response.body())
                } else {
                    if (response.message().isNullOrEmpty()) {
                        errorMessage.value = "An unknown error occured"
                    } else {
                        errorMessage.value = response.message()
                    }
                }
            } catch (e: Exception) {
                errorMessage.value = e.message
            }
            finally {
                isLoading.value = false
            }
        }
    }

    fun addToBox(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ) {
        viewModelScope.launch {
            repository.addToBox(
                yemek_adi,
                yemek_resim_adi,
                yemek_fiyat,
                yemek_siparis_adet,
                kullanici_adi
            )
        }

    }
}