package com.example.retrofit_post_api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

var baseurl = "https://reqres.in/api/"

interface CreateUserInterface {

    @POST("users")
    fun createuser(@Body model:UserModel):Call<Receive_response_from_api>


    // 2nd Method to hit post
//    @FormUrlEncoded
//    @POST("users")
//    fun createuser2(@Field("id") id:Int, @Field("email")email:String,
//    @Field("first_name") first_name:String, @Field("last_name")last_name:String,@Field("avatar")avatar:String):Call<Receive_response_from_api>

//    @Multipart
//    @POST("users")
//    fun createuser3(@Part("id")id:Int,@Part("email")email:String,
//   @Part("first_name") first_name:String, @Part("last_name")last_name:String,@Part("avatar")avatar:String):Call<Receive_response_from_api>

}

 object User_retrofit_object{

 var interf:CreateUserInterface
     init {
         var retrofit = Retrofit.Builder()
             .baseUrl(baseurl)
             .addConverterFactory(GsonConverterFactory.create())
             .build()

         interf = retrofit.create(CreateUserInterface::class.java)
     }

 }