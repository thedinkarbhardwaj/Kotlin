package com.example.retrofitdelete

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

    fun deletedata(view: View) {

        retrofit_function()
    }

    fun retrofit_function(){

        var method_call = Product_retrofit_object.product_inteface.delete_product()
        method_call.enqueue(object : Callback<ProductResponse>{
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                var res = response.body()
                Log.d("TAG", "isDeleted: " + res?.isDeleted.toString() + "\n" + "Deleted On: " + res?.deletedOn.toString())
                Toast.makeText(this@MainActivity, "isDeleted: " + res?.isDeleted.toString() + "Deleted On: " + res?.deletedOn.toString(), Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Log.d("TAG", "onFailure: " + t.message.toString())
                Toast.makeText(this@MainActivity, "Err " + t.message.toString(), Toast.LENGTH_LONG).show()
            }

        })

    }
}