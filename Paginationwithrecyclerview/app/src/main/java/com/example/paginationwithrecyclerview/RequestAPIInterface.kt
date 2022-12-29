package com.example.paginationwithrecyclerview

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.json.JSONArray
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RequestAPIInterface {
    @GET("posts")
    fun fetchData(): Call<JsonArray>
}