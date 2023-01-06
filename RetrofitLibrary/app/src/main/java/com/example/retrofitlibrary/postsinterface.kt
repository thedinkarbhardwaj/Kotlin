package com.example.retrofitlibrary

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST


const val baseurl:String = "https://jsonplaceholder.typicode.com/"

 interface postsinterfacecheck{

     @GET("posts")
     fun getposts():Call<dataclassofposts>

 }

 object postinstance{
     val postinter:postsinterfacecheck
     init {
         val retrofit: Retrofit = Retrofit.Builder()
             .baseUrl(baseurl)
             .addConverterFactory(GsonConverterFactory.create())
             .build()
         postinter = retrofit.create(postsinterfacecheck::class.java)
     }
 }