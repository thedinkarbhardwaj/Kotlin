package com.example.multipleapicallsretrofit

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


val baseUrl = "https://jsonplaceholder.typicode.com/"

//val baseUrl = "https://dummyjson.com/"


interface Interface {

    @GET("{posts}")
    fun getpostsdata(@Path("posts") posts: String): Call<List<POST_RESPONSES>>

    @GET("{comments}")
    fun getcommentdata(@Path("comments") comments: String): Call<List<COMMENTS_RESPONSE>>


    @GET("products/1")
    fun getstart_object_data(): Call<product_response>

    @GET("products")
    fun getstart_object_data_for_all_product(): Call<ALL_PRODUCT_RESPONSE>

}

object CurrencyService {

    val currency_interface: Interface

    init {

        val retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        currency_interface = retrofit.create(Interface::class.java)


    }

}