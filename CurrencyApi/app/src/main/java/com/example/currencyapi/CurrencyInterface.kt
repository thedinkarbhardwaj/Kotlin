package com.example.currencyapi

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


val baseUrl = "https://api.apilayer.com/"


interface CurrencyInterface {

    @Headers("apikey: Apikey")
    @GET("/exchangerates_data/convert?")
     fun getcurrencydata(@Query("to") to:String,@Query("from") from:String,@Query("amount") amount:String):Call<response_data>
}

object CurrencyService{

    val currency_interface:CurrencyInterface
    init {

       val retrofit =  Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        currency_interface = retrofit.create(CurrencyInterface::class.java)

    }

}
