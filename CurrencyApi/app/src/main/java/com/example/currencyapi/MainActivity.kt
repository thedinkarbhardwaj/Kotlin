package com.example.currencyapi

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var textview:TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textview = findViewById(R.id.textview)

        getcurrency()
    }

    private fun getcurrency() {

        val result = CurrencyService.currency_interface.getcurrencydata("INR","EUR","5");
        result.enqueue(object : Callback<response_data>{
            override fun onResponse(call: Call<response_data>, response: Response<response_data>) {

                Log.d("Error","success "+response.body()?.result.toString())
                textview.setText("Convert value is : " + response.body()?.result.toString())
            }

            override fun onFailure(call: Call<response_data>, t: Throwable) {

                Log.d("Error","error "+t.message.toString())
            }

        })
    }
}