package com.example.retrofit_library_with_param

data class PostModel(var data:List<datamodel>)

data class datamodel(var id:String,var image:String,var likes:Int,var tags:List<String>,var text:String,var publishDate:String,var owner:ownerdatamodel)

data class ownerdatamodel(var id:String,var title:String,var firstName:String,var lastName:String)
