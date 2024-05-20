package com.samet.orderapp

import android.content.Intent
import android.os.Bundle
import android.widget.RatingBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.samet.orderapp.databinding.ActivityProductDetailBinding
import com.samet.orderapp.model.Yemekler
import com.samet.orderapp.ui.viewmodels.BoxFragmentViewModel
import com.samet.orderapp.ui.viewmodels.MainFragmentViewModel
import com.samet.orderapp.util.Constans
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random
@AndroidEntryPoint
class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    private lateinit var viewModel: MainFragmentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val tempViewModel: MainFragmentViewModel by viewModels()
        viewModel=tempViewModel

        val getValue:Yemekler= intent.getSerializableExtra("yemek") as Yemekler
        binding.textViewFoodAmount.text= getValue.yemek_fiyat+" "+"TL"

        binding.textViewFoodName.text= getValue.yemek_adi

        Glide.with(this).load(Constans.BASE_URL + "yemekler/resimler/" +getValue.yemek_resim_adi).centerCrop()
            .placeholder(R.drawable.ic_launcher_background).into(binding.imageViewFoodView)

        binding.imageViewBackButton.setOnClickListener {
            //onBackPressed()
            finish()
        }

        val randomNumber:Float = Random.nextInt(1,5).toFloat()
        binding.ratingBar.rating=randomNumber
        binding.ratingBar.setIsIndicator(true)// ratingbarda kullanıcı herhangi bir değişiklik yapamasın komutu

        binding.textViewTotalAmount.text = totalAmount(getValue.yemek_fiyat,binding.textViewProductPiece.text.toString()) + " " + "TL"

        binding.imageViewAddButton.setOnClickListener {
            increasePiece()
            binding.textViewTotalAmount.text = totalAmount(getValue.yemek_fiyat,binding.textViewProductPiece.text.toString()) + " " + "TL"
        }
        binding.imageViewSubtractionButton.setOnClickListener {
            subtractionPiece()
            binding.textViewTotalAmount.text = totalAmount(getValue.yemek_fiyat,binding.textViewProductPiece.text.toString()) + " " + "TL"
        }
        binding.textviewAddToBoxButton.setOnClickListener {
            val siparis_adet = binding.textViewProductPiece.text.toString()
            viewModel.addToBox(getValue.yemek_adi,getValue.yemek_resim_adi,getValue.yemek_fiyat.toInt(),siparis_adet.toInt(),"Samet")
            Toast.makeText(this, "Ürününüz sepete eklenmiştir.", Toast.LENGTH_SHORT).show()
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun increasePiece() {
        var piece= binding.textViewProductPiece.text.toString()
        if (piece.isNotEmpty()){
            var pieceInt= piece.toInt()
            pieceInt++
            binding.textViewProductPiece.text= pieceInt.toString()
        }
    }

    private fun subtractionPiece() {
        var piece= binding.textViewProductPiece.text.toString()
        if (piece.isNotEmpty()){
            var pieceInt= piece.toInt()
            if (pieceInt != 1){
                pieceInt--
            }
            binding.textViewProductPiece.text= pieceInt.toString()
        }
    }

    private fun totalAmount(foodAmount:String,piece:String): String{
        val foodAmountInt= foodAmount.toInt()
        val pieceInt= piece.toInt()
        val totalAmount:Int = foodAmountInt * pieceInt
        return totalAmount.toString()

    }


}