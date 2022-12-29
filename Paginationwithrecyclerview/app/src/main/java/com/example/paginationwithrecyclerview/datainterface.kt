package com.example.paginationwithrecyclerview

import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST


const val baseurl:String = "https://jsonplaceholder.typicode.com/"

interface datainterface{

    @GET("posts")
    fun getposts():Call<ItemsViewModel>

}

//object datainstance{
//    val datainter:datainterface
//    init {
//        val retrofit: Retrofit = Retrofit.Builder()
//            .baseUrl(baseurl)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        datainter = retrofit.create(datainterface::class.java)
//    }
//}