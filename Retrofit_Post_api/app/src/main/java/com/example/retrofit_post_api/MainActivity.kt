package com.example.retrofit_post_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun post_api_retrofit(view: View) {
        retrofit_fun()
    }

    fun retrofit_fun(){

        var data:UserModel = UserModel(8,"hherry@gmail.com","Herry","Potter","Url")
        var result = User_retrofit_object.interf.createuser(data)
        result.enqueue(object :Callback<Receive_response_from_api>{
            override fun onResponse(call: Call<Receive_response_from_api>, response: Response<Receive_response_from_api>) {
                var  res = response.body()
                Log.d("TAG", "onResponse: " + res?.id?.toString() + res?.createdAt.toString())
                Toast.makeText(this@MainActivity, "ID "+res?.id?.toString() + "\n Creaed At" +  res?.createdAt.toString(), Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<Receive_response_from_api>, t: Throwable) {
                Log.d("TAG", "Error: " + t.message.toString())
                Toast.makeText(this@MainActivity, ""+t.message.toString(), Toast.LENGTH_LONG).show()
            }

        })

    }
}