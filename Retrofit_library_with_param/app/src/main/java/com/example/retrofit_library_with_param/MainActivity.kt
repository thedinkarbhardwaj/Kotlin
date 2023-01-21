package com.example.retrofit_library_with_param

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var callapi: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callapi = findViewById(R.id.callapi)

        callapi.setOnClickListener(View.OnClickListener {
            retrofit_functionality()
        })

    }

    fun retrofit_functionality(){

        var methodcall = Retrofit_object_create.interfa.post("10")
        methodcall.enqueue(object :Callback<PostModel>{
            override fun onResponse(call: Call<PostModel>, response: Response<PostModel>) {
                var res = response.body()
                Log.d("datamodel: data", "datamodel: data " + res?.data?.get(9)?.likes.toString())
                Log.d("text", "Text: "+ res?.data?.get(9)?.text.toString())
                Log.d("ownerdatamodel", "ownerdatamodel: " + res?.data?.get(9)?.owner?.title)
            }

            override fun onFailure(call: Call<PostModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error " + t.message.toString(), Toast.LENGTH_LONG).show()
                Log.d("Error", "onFailure: " + t.message.toString())
            }

        })

    }
}