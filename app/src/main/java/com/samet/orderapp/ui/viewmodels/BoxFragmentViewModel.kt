package com.samet.orderapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samet.orderapp.model.FoodResponse
import com.samet.orderapp.model.GetBoxResponse
import com.samet.orderapp.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoxFragmentViewModel @Inject constructor(private val repository: FoodRepository) : ViewModel() {

    val boxList = MutableLiveData<GetBoxResponse?>()
    val emptyBoxText: MutableLiveData<String?> = MutableLiveData()
    val arrayList: ArrayList<GetBoxResponse?> = ArrayList()
    init {
        getBoxFoods()
    }
    fun getBoxFoods() {
        viewModelScope.launch {
            try {
                val response = repository.getBox("Samet")
                if (response.isSuccessful) {
                    boxList.postValue(response.body())
                } else {
                        emptyBoxText.value = "There is not any product"
                }
            } catch (e: Exception) {
                emptyBoxText.value = "There is not any product"
            }
        }
    }

    fun ok() {
        viewModelScope.launch{
            try {
                val response = repository.getBox("Samet")
                if (response.isSuccessful) {
                    response.body()?.let { boxResponse ->
                        boxResponse.sepet_yemekler.forEach { sepetYemek ->
                            deleteBox(sepetYemek.sepet_yemek_id)
                        }
                    }
                }
            } catch (e: Exception) {
                println("Hata oluştu ${e}")
            }

        }
    }


    fun deleteBox(kisi_Id:Int){
        viewModelScope.launch{
            repository.deleteBox(kisi_Id,"Samet")
            getBoxFoods()
        }
    }

}