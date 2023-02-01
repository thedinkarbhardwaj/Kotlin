package com.example.bouncynestedscrollview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    lateinit var listview:ListView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
//        var listdata = mutableListOf<String>();
//
//        for (i in 0..20){
//            listdata.add(i,"Text " + i.toString())
//        }
//
//        listview = findViewById(R.id.listview)
//
//        var adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listdata)
//        listview.adapter = adapter

    }
}