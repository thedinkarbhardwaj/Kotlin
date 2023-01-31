package com.example.rowproduct

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var recyclerview:RecyclerView



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview = findViewById(R.id.recyclerview)

        var gridLayout = GridLayoutManager(this,3)
        recyclerview.layoutManager =gridLayout

        var adapter = Adapter(this@MainActivity)
        recyclerview.adapter = adapter

    }

}