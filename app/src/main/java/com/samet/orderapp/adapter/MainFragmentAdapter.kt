package com.samet.orderapp.adapter

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.samet.orderapp.MainActivity
import com.samet.orderapp.ProductDetailActivity
import com.samet.orderapp.R
import com.samet.orderapp.model.Yemekler
import com.samet.orderapp.ui.MainFragment
import com.samet.orderapp.util.Constans.Companion.BASE_URL


class MainFragmentAdapter:RecyclerView.Adapter<MainFragmentAdapter.MainFragmentViewHolder>() {
    inner class MainFragmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    lateinit var cardView: CardView
    lateinit var addFavorite: ImageView
    lateinit var foodImage: ImageView
    lateinit var foodText: TextView
    lateinit var foodAmount: TextView
    lateinit var addBox: ImageView

    private val differCallBack = object: DiffUtil.ItemCallback<Yemekler>(){
        override fun areItemsTheSame(oldItem: Yemekler, newItem: Yemekler): Boolean {
            return oldItem.yemek_id == newItem.yemek_id
        }

        override fun areContentsTheSame(oldItem: Yemekler, newItem: Yemekler): Boolean {
            return oldItem == newItem
        }
    }
    val differ= AsyncListDiffer(this,differCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFragmentViewHolder {
        return MainFragmentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.main_recyclerview_row,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MainFragmentViewHolder, position: Int) {
        val yemek= differ.currentList[position]

        cardView= holder.itemView.findViewById(R.id.cardView)
        addFavorite= holder.itemView.findViewById(R.id.add_favourite)
        foodImage= holder.itemView.findViewById(R.id.food_image)
        foodText= holder.itemView.findViewById(R.id.food_name)
        foodAmount= holder.itemView.findViewById(R.id.amount)
        addBox= holder.itemView.findViewById(R.id.add_box)

        holder.itemView.apply {
            foodText.text= yemek.yemek_adi
            foodAmount.text= yemek.yemek_fiyat +" "+ "TL"
            Glide.with(this).load(BASE_URL+ "yemekler/resimler/" +yemek.yemek_resim_adi).centerCrop()
                .placeholder(R.drawable.ic_launcher_background).into(foodImage)
            setOnClickListener {
                val intent= Intent(holder.itemView.context,ProductDetailActivity::class.java)
                intent.putExtra("yemek",yemek)
                holder.itemView.context.startActivity(intent)
            }
        }
    }

}