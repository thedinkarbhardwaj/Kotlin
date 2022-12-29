package com.example.paginationwithrecyclerview

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.google.gson.JsonArray
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call


class MainActivity : AppCompatActivity() {

    var isScroll = false;
    var currentitems:Int = 0;
    var scroll_out_items:Int = 0;
    var totalitems:Int = 0;
    var i = 0;
    var index = 15;
    lateinit var data:ArrayList<ItemsViewModel>
    lateinit var datadummy:ArrayList<ItemsViewModel>
    lateinit var adapter:CustomAdapter
    lateinit var progress:ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiCall()
        data = ArrayList()

        datadummy = ArrayList()

        for (i in 0..15){
            datadummy.add(ItemsViewModel(i,"index "+ i.toString(),"body "+i.toString()))
        }

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        progress = findViewById(R.id.progress);

        var manager = LinearLayoutManager(this);

        recyclerview.layoutManager = manager;

         adapter = CustomAdapter(datadummy)

        recyclerview.adapter = adapter

        recyclerview.addOnScrollListener(object : OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){

                    isScroll = true

                }

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                currentitems = manager.childCount // childCount method call for get current items number display in screen
                totalitems = manager.itemCount // itemCount method call for how many data store in adapter
                scroll_out_items = manager.findFirstVisibleItemPosition()

                Log.d("items","currentitems " + currentitems.toString())
                Log.d("items","totalitems " + totalitems.toString())
                Log.d("items","scroll_out_items " + scroll_out_items.toString())

                Toast.makeText(this@MainActivity,"currentitems " + currentitems.toString(),Toast.LENGTH_LONG).show()
                Toast.makeText(this@MainActivity,"totalitems " + totalitems.toString(),Toast.LENGTH_LONG).show()
                Toast.makeText(this@MainActivity,"scroll_out_items " + scroll_out_items.toString(),Toast.LENGTH_LONG).show()



                    if ((currentitems + scroll_out_items) == totalitems ) {
                        progress.visibility = View.VISIBLE

 Toast.makeText(this@MainActivity,data.count().toString(),Toast.LENGTH_LONG).show()
                        if (index<=data.count()){
                        Toast.makeText(this@MainActivity,"calll" ,Toast.LENGTH_LONG).show()
                        var ten = 0;

                            outer@ for(i in 1..10){
                                var d = data.get(index);
                                datadummy.add(d)
                                ten++;

                                index++;

                                Toast.makeText(this@MainActivity,"tendata"+ten,Toast.LENGTH_LONG).show()
                                if (ten == 10){

                                    Log.d("ten","Donennnnfh")
                                    break@outer
                                }
                            }
//                        data.forEachIndexed {index, element ->
//
//                            var d = data.get(index);
//                            datadummy.add(d)
//                            ten++;
//
//                            Toast.makeText(this@MainActivity,"tendata"+ten,Toast.LENGTH_LONG).show()
//                            if (ten == 10){
//
//                                Log.d("ten","Donennnnfh")
//                                i+10;
//
//                            }
//                        }

                        Toast.makeText(this@MainActivity,"Notify",Toast.LENGTH_LONG).show()
                            progress.visibility = View.GONE
                        adapter.notifyDataSetChanged()
                    }
                    }

            }
        })
    }


    fun apiCall() {

                val requestInterface =
                    APIClient.getClient().create(RequestAPIInterface::class.java)
                val response =
                    requestInterface.fetchData()
                response.enqueue(object : retrofit2.Callback<JsonArray> {
                    override fun onResponse(
                        call: Call<JsonArray>,
                        response: retrofit2.Response<JsonArray>
                    ) {
                        if (response.isSuccessful) {
                            if (response.body() != null) {
                                val resp = response.body()
                                if (resp != null) {
                                    Log.e("dewdewd", resp.toString())
                                    var  dataArray = JSONArray(resp.toString())
                                    for (i in 0 until dataArray.length()) {
                                        val jsonObject: JSONObject = dataArray.getJSONObject(i)
                                        var uid = jsonObject.getString("id")
                                        var title = jsonObject.getString("title")
                                        var body = jsonObject.getString("title")
                                        Log.e("id", uid)

                                        data.add(ItemsViewModel(uid.toInt(),title,body))
                                        progress.visibility = View.GONE
                                    }

                                } else {
                                    //progressDialog.dismiss()
                                    Log.e("dewdewd","null")

                                }
                            }
                        } else {
                            Log.e("dewdewd", "try again")

//                    alertError(getString(R.string.try_again))
                        }
                    }

                    override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                        Log.e("Throwable", t.message.toString())
//                        alertError(t.message.toString())
//                        progressDialog.dismiss()
                    }
                })

        // }
    }
}