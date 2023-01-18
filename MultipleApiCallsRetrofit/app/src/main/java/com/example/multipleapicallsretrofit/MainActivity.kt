package com.example.multipleapicallsretrofit

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.multipleapicallsretrofit.adapter.RecyclerviewAdapter
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var recyclerview:RecyclerView
    lateinit var adapter:RecyclerviewAdapter

    var store_data_after_response = mutableListOf<Any>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)

//        object_response_api_call_single_product()
//        object_response_api_call_all_product()

        simpleobserver()
    }

    private fun simpleobserver() {

        var list = listOf<String>("comments","posts");

        val observable = Observable.fromIterable(list)

        observable.subscribe(object : Observer<String>{
            override fun onSubscribe(d: Disposable) {
                Log.d("observer","Subscribe is start")
            }

            override fun onNext(t: String) {

                Toast.makeText(this@MainActivity,"data "+t.toString(),Toast.LENGTH_LONG).show()
                GlobalScope.async(Dispatchers.Main) {
                    getapicall(t);
               }
                Log.d("observer","OnNext is start")
                Log.d("observer"," Spaces  ")
            }

            override fun onError(e: Throwable) {
                Log.d("observer","Error"+e.message)
            }

            override fun onComplete() {

                Toast.makeText(applicationContext,"zcompleted",Toast.LENGTH_LONG).show()
                Log.d("observer","Complete is start")
            }

        })

    }

    private suspend fun getapicall(url:String) {

        lateinit var api: Deferred<Unit>
        when(url) {
            "posts" -> {

                posts_api_call_function(url)

            }
            "comments" -> {

                api = GlobalScope.async(Dispatchers.Main) {


                    var result = CurrencyService.currency_interface.getcommentdata(url);
                    result.enqueue(object : Callback<List<COMMENTS_RESPONSE>> {
                        override fun onResponse(
                            call: Call<List<COMMENTS_RESPONSE>>,
                            response: Response<List<COMMENTS_RESPONSE>>
                        ) {

                            var data = response.body()
                            for (i in data!!) {
                                Log.d("check", "success " + i.body)
                                store_data_after_response.add(i.body);
                            }
                            adapter_call_function();
                            Log.d("check", "End ")
                            Toast.makeText(
                                this@MainActivity,
                                "list" + store_data_after_response.lastIndex.toString(),
                                Toast.LENGTH_LONG
                            ).show()

                        }

                        override fun onFailure(call: Call<List<COMMENTS_RESPONSE>>, t: Throwable) {

                            Log.d("check", "error " + t.message.toString())
                        }

                    })

                }
             //   api.join() // join fun used to block all threads until coroutine does not complete his work

                api.await()
                Log.d("await","Await2")
            }

        }

    }

    private fun adapter_call_function() {
        adapter = RecyclerviewAdapter(store_data_after_response)

        recyclerview.adapter = adapter
    }

    suspend fun posts_api_call_function(url:String){
        var api = GlobalScope.async(Dispatchers.Main) {

            var result = CurrencyService.currency_interface.getpostsdata(url);
            result.enqueue(object : Callback<List<POST_RESPONSES>> {
                override fun onResponse(
                    call: Call<List<POST_RESPONSES>>,
                    response: Response<List<POST_RESPONSES>>
                ) {

                    var data = response.body()
                    for (i in data!!) {
                        Log.d("check", "success " + i.title)
                        store_data_after_response.add(i.title);
                    }
                    adapter_call_function()
                    Log.d("check", "End ")

                }

                override fun onFailure(call: Call<List<POST_RESPONSES>>, t: Throwable) {

                    Log.d("check", "error " + t.message.toString())
                }

            })


        }
        api.await()
        Log.d("await","Await")

        api.join()

        api.await()
        Log.d("await","work")

    }

   fun object_response_api_call_single_product(){
       var result = CurrencyService.currency_interface.getstart_object_data();
       result.enqueue(object : Callback<product_response>{
           override fun onResponse(
               call: Call<product_response>,
               response: Response<product_response>
           ) {
               var res = response.body()
               Toast.makeText(this@MainActivity,"response " + res?.title,Toast.LENGTH_LONG).show()
               Log.d("product_response","Data " + res?.images)
           }

           override fun onFailure(call: Call<product_response>, t: Throwable) {
              Log.d("product_response","Err " + t.message)
           }

       })
   }


    fun object_response_api_call_all_product(){
        var result = CurrencyService.currency_interface.getstart_object_data_for_all_product();
        result.enqueue(object : Callback<ALL_PRODUCT_RESPONSE>{
            override fun onResponse(
                call: Call<ALL_PRODUCT_RESPONSE>,
                response: Response<ALL_PRODUCT_RESPONSE>
            ) {
                var res = response.body()
                Toast.makeText(this@MainActivity,"response " + res?.total,Toast.LENGTH_LONG).show()
                Log.d("product_response","Data " + res?.products?.get(0)?.title)
            }

            override fun onFailure(call: Call<ALL_PRODUCT_RESPONSE>, t: Throwable) {
                Log.d("product_response","Err " + t.message)
            }


        })
    }

}