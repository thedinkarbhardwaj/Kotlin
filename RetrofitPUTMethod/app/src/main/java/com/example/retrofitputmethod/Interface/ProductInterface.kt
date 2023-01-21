package com.example.retrofitputmethod.Interface

import com.example.retrofitputmethod.ProductResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.PUT
import retrofit2.http.Part


var baseurl = "https://dummyjson.com"
interface ProductInterface {


    @Multipart
    @PUT("products/1")
    fun edit_product(@Part("id") id:Int,@Part("title") title:String,@Part("price") price:Int):Call<ProductResponse>

}

object Product_retrofit_object{

    var product_inteface:ProductInterface
    init {
        var retrofit = Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        product_inteface = retrofit.create(ProductInterface::class.java)
    }

}