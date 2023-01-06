package com.example.retrofitlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.retrofitlibrary.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val post: Call<dataclassofposts> = postinstance.postinter.getposts()
        post.enqueue(object :Callback<dataclassofposts>{
            override fun onResponse(
                call: Call<dataclassofposts>?, response: Response<dataclassofposts>?
            ) {

                var title = response?.body()?.title
                Toast.makeText(this@MainActivity,""+title,Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<dataclassofposts>?, t: Throwable?) {
                Toast.makeText(this@MainActivity,"Error "+t?.message,Toast.LENGTH_LONG ).show()


            }

        })

    }
}