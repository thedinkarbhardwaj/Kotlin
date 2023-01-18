package com.example.multipleapicallsretrofit

data class ALL_PRODUCT_RESPONSE(
    var products:List<product>,
    var total:Int,
    var skip:Int,
    var limit:Int

)

data class product(
    var id:Int,
    var title:String
)
