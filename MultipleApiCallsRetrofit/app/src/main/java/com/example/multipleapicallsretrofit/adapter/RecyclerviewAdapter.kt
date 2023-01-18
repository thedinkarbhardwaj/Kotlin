package com.example.multipleapicallsretrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.multipleapicallsretrofit.R


class RecyclerviewAdapter(var store_data_after_response: MutableList<Any>) : RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.customlayout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtview.text = store_data_after_response.get(position).toString()
        holder.number.text = position.toString()
    }

    override fun getItemCount(): Int {
        return store_data_after_response.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var txtview = itemView.findViewById<TextView>(R.id.textView)
        var number = itemView.findViewById<TextView>(R.id.number)

    }
}