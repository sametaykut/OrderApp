package com.samet.orderapp.retrofit

import com.samet.orderapp.model.CrudResponse
import com.samet.orderapp.model.FoodResponse
import com.samet.orderapp.model.GetBoxResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductAPI {

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun getFoods():Response<FoodResponse>

    @FormUrlEncoded
    @POST("yemekler/sepeteYemekEkle.php")
    suspend fun addToBox(@Field("yemek_adi") yemek_adi:String,
                         @Field("yemek_resim_adi")  yemek_resim_adi:String,
                         @Field("yemek_fiyat") yemek_fiyat:Int,
                         @Field("yemek_siparis_adet") yemek_siparis_adet:Int,
                         @Field("kullanici_adi") kullanici_adi:String):Response<CrudResponse>

    @FormUrlEncoded
    @POST("yemekler/sepettekiYemekleriGetir.php")
    suspend fun getBox(@Field("kullanici_adi") kullanici_adi:String):Response<GetBoxResponse>

    @FormUrlEncoded
    @POST("yemekler/sepettenYemekSil.php")
    suspend fun deleteBox(@Field("sepet_yemek_id") sepet_yemek_id:Int,
                          @Field("kullanici_adi") kullanici_adi:String):Response<GetBoxResponse>


}