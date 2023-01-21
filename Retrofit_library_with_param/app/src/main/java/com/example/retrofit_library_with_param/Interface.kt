package com.example.retrofit_library_with_param

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

var baseurl = "https://dummyapi.io"
interface Interface {

    @Headers("app-id: AppId")
    @GET("/data/v1/tag/water/post?")
    fun post(@Query("limit") limit:String): Call<PostModel>

}

object Retrofit_object_create{

    val interfa:Interface

    init {
        var retrofit = Retrofit.Builder().baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        interfa = retrofit.create(Interface::class.java)
    }



}