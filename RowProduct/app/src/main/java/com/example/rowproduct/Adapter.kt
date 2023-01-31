package com.example.rowproduct

import android.animation.ObjectAnimator
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class Adapter(mainActivity: MainActivity) : RecyclerView.Adapter<Adapter.Viewholder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.custom_layout,parent,false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {

        holder.add_img.setOnClickListener(View.OnClickListener {
            holder.minus_img.visibility = View.VISIBLE
            holder.product_textview.visibility =View.VISIBLE

            ObjectAnimator.ofArgb(holder.cardView, "cardBackgroundColor", Color.RED).apply {
                duration = 2000
                start()
            }

//            ObjectAnimator.ofArgb(holder.minus_img, "visibility", View.VISIBLE).apply {
//                duration = 1000
//                start()
//            }
//
//            ObjectAnimator.ofArgb(holder.product_textview, "visibility", View.VISIBLE).apply {
//                duration = 1000
//                start()
//            }
        })
    }

    override fun getItemCount(): Int {
        return 20
    }

    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var cardView = itemView.findViewById<CardView>(R.id.maincardview1)
        var add_img = itemView.findViewById<ImageView>(R.id.add_img)
        var minus_img = itemView.findViewById<ImageView>(R.id.minusimg)
        var product_textview = itemView.findViewById<TextView>(R.id.product_text)
    }
}