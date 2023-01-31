package com.example.bottomnavigationbar_with_meowbottomnavigationlibrary

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.etebarian.meowbottomnavigation.MeowBottomNavigation


class MainActivity : AppCompatActivity() {

    lateinit var bottom_navigationbar:MeowBottomNavigation

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigationbar = findViewById(R.id.bottom_navigationbar)

        bottom_navigationbar.add(MeowBottomNavigation.Model(1,R.drawable.ic_baseline_home_24))
        bottom_navigationbar.add(MeowBottomNavigation.Model(2,R.drawable.ic_baseline_explore_24))
        bottom_navigationbar.add(MeowBottomNavigation.Model(3,R.drawable.ic_baseline_message_24))

        bottom_navigationbar.setOnShowListener {
            // YOUR CODES
        }
        
        bottom_navigationbar.setOnClickMenuListener { 
            when(it.id){
                1->{
                    Toast.makeText(this@MainActivity, "Home", Toast.LENGTH_LONG).show()
                }
                2->{
                    Toast.makeText(this@MainActivity, "Explorer", Toast.LENGTH_LONG).show()
                }
                3->{
                    Toast.makeText(this@MainActivity, "Message", Toast.LENGTH_LONG).show()
                }
            }
        }
        
        bottom_navigationbar.setOnReselectListener {
            Toast.makeText(this@MainActivity, "Reselect", Toast.LENGTH_LONG).show()
        }
    }
}