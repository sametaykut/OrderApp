package com.samet.orderapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.samet.orderapp.databinding.ActivityProductDetailBinding
import com.samet.orderapp.model.Yemekler
import com.samet.orderapp.util.Constans

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getValue:Yemekler= intent.getSerializableExtra("yemek") as Yemekler
        binding.textViewFoodAmount.text= getValue.yemek_fiyat+" "+"TL"
        binding.textViewFoodName.text= getValue.yemek_adi
        Glide.with(this).load(Constans.BASE_URL + "yemekler/resimler/" +getValue.yemek_resim_adi).centerCrop()
            .placeholder(R.drawable.ic_launcher_background).into(binding.imageViewFoodView)
        binding.textViewTotalAmount.text = totalAmount(getValue.yemek_fiyat,binding.textViewProductPiece.text.toString()) + " " + "TL"
    }

    private fun totalAmount(foodAmount:String,piece:String): String{
        val foodAmountInt= foodAmount.toInt()
        val pieceInt= piece.toInt()
        val totalAmount:Int = foodAmountInt * pieceInt
        return totalAmount.toString()

    }
}