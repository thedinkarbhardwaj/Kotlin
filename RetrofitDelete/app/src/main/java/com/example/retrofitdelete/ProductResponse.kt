package com.example.retrofitdelete

data class ProductResponse(var id:Int,var title:String,var description:String,var isDeleted:Boolean,
var deletedOn:String)
