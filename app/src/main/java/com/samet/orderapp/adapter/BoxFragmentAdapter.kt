package com.samet.orderapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.samet.orderapp.ProductDetailActivity
import com.samet.orderapp.R
import com.samet.orderapp.model.SepetYemekler
import com.samet.orderapp.model.Yemekler
import com.samet.orderapp.ui.viewmodels.BoxFragmentViewModel
import com.samet.orderapp.util.Constans

class BoxFragmentAdapter(val viewModel:BoxFragmentViewModel):RecyclerView.Adapter<BoxFragmentAdapter.BoxFragmentViewHolder>() {
    inner class BoxFragmentViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    lateinit var cardView: CardView
    lateinit var foodImage: ImageView
    lateinit var foodText: TextView
    lateinit var foodAmount: TextView
    lateinit var foodPiece: TextView
    lateinit var deleteImage:ImageView
    lateinit var totalAmount: TextView

    private val differCallBack = object: DiffUtil.ItemCallback<SepetYemekler>(){
        override fun areItemsTheSame(oldItem: SepetYemekler, newItem: SepetYemekler): Boolean {
            return oldItem.sepet_yemek_id == newItem.sepet_yemek_id
        }

        override fun areContentsTheSame(oldItem: SepetYemekler, newItem: SepetYemekler): Boolean {
            return oldItem == newItem
        }
    }
    val differ= AsyncListDiffer(this,differCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoxFragmentViewHolder {
        return BoxFragmentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.box_recyclerview_row,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: BoxFragmentViewHolder, position: Int) {
        val yemek= differ.currentList[position]

        cardView= holder.itemView.findViewById(R.id.cardViewBox)
        foodImage= holder.itemView.findViewById(R.id.food_image_recyclerview)
        foodText= holder.itemView.findViewById(R.id.food_name_recyclerview)
        foodAmount= holder.itemView.findViewById(R.id.food_amount_recyclerview)
        foodPiece= holder.itemView.findViewById(R.id.food_adet_recyclerview)
        deleteImage= holder.itemView.findViewById(R.id.delete_button)
        totalAmount= holder.itemView.findViewById(R.id.total_amount_recyclerview)


        holder.itemView.apply {
            foodText.text= yemek.yemek_adi
            foodAmount.text= yemek.yemek_fiyat +" "+ "TL"
            Glide.with(this).load(Constans.BASE_URL + "yemekler/resimler/" +yemek.yemek_resim_adi).centerCrop()
                .placeholder(R.drawable.ic_launcher_background).into(foodImage)
            foodPiece.text= yemek.yemek_siparis_adet
            totalAmount.text= (yemek.yemek_fiyat.toDouble() * yemek.yemek_siparis_adet.toInt()).toString()+" "+"TL"
            deleteImage.setOnClickListener {
                viewModel.deleteBox(yemek.sepet_yemek_id)
                Toast.makeText(holder.itemView.context, "Ürününüz sepete silinmiştir.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}