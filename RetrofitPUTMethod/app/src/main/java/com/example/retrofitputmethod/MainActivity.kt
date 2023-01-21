package com.example.retrofitputmethod

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.retrofitputmethod.Interface.Product_retrofit_object
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    lateinit var id:EditText
    lateinit var title:EditText
    lateinit var price:EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        id = findViewById(R.id.id)
        title = findViewById(R.id.title)
        price = findViewById(R.id.price)
    }

    fun submit(view: View) {
        var idd = Integer.parseInt(id.text.toString())
        var titlee = title.text.toString()
        var pricee = Integer.parseInt(price.text.toString())

        retrofit_fun(idd,titlee,pricee)
    }

    fun retrofit_fun(idd:Int,titl:String,pricee:Int){

        var method_call = Product_retrofit_object.product_inteface.edit_product(idd,titl,pricee)
        method_call.enqueue(object:Callback<ProductResponse>{
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                var res = response.body()
                Log.d("TAG", "Title: "+ res?.title.toString() + " Price: " + res?.price.toString())
                Toast.makeText(this@MainActivity, ""+res?.title.toString() + " Price: " + res?.price.toString(), Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Log.d("TAG", "onFailure: "+ t.message.toString())
                Toast.makeText(this@MainActivity, "Error " + t.message.toString(), Toast.LENGTH_LONG).show()
            }

        })

    }


}
