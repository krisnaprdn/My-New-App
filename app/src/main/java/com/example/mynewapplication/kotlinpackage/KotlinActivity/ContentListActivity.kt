package com.example.mynewapplication.kotlinpackage.KotlinActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynewapplication.adapter.ListAdapter
import com.example.mynewapplication.R
import com.example.mynewapplication.kotlinpackage.ApiServicesKotlin
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_content_list.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class ContentListActivity : AppCompatActivity() {

    private lateinit var imageButton: ImageButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ListAdapter
//    private var result: List<Result> = ArrayList()
    var linearLayoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_list)

        val apiServiceKotlin = ApiServicesKotlin()

//        ib_back.setOnClickListener {
////            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//        }


//        init()
//        initLiveData()
        initView()
        getData()
//        rv_contentlist.setHasFixedSize(true)
//        rv_contentlist.layoutManager = LinearLayoutManager(this)
//        adapter = ListAdapter(this)
//        rv_contentlist.adapter = adapter
    }

    fun initView(){
        rv_contentlist.layoutManager = linearLayoutManager
        adapter = ListAdapter(this)
        rv_contentlist.adapter = adapter
    }

    fun getData(){
        val request = Request.Builder()
                .url("http://www.recipepuppy.com")
                .build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.e("Failed", e.message)
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val body = response.body()?.string()
                var gson = GsonBuilder().create()
//                var resultGson = gson.fromJson(body, Array<Result>::class.java).toList()
//                adapter.setResult(resultGson)
            }
        })
    }

//    private fun initLiveData(){
//        RetrofitClient.INSTANCE.getRecipes().enqueue(object : Callback<List<Result>>{
//            override fun onResponse(call: Call<List<Result>>, response: Response<List<Result>>) {
////                response.body()?.let { result.addAll(it)}
////                val adapter = ListAdapter(list)
////                rv_contentlist.adapter = adapter
//            }
//
//            override fun onFailure(call: Call<List<Result>>, t: Throwable) {
//
//            }
//
//        })
//    }

//    private fun init() {
//        recyclerView = findViewById(R.id.rv_contentlist)
//
//        val data = ArrayList<ListModel>()
//        data.add(ListModel(R.drawable.food, "Food 1", "Food Desc", "Detail 1", "Detail 2", "Detail 3", "See Detail"))
//        data.add(ListModel(R.drawable.food, "Food 1", "Food Desc", "Detail 1", "Detail 2", "Detail 3", "See Detail"))
//        data.add(ListModel(R.drawable.food, "Food 1", "Food Desc", "Detail 1", "Detail 2", "Detail 3", "See Detail"))
//        data.add(ListModel(R.drawable.food, "Food 1", "Food Desc", "Detail 1", "Detail 2", "Detail 3", "See Detail"))
//        data.add(ListModel(R.drawable.food, "Food 1", "Food Desc", "Detail 1", "Detail 2", "Detail 3", "See Detail"))
//        data.add(ListModel(R.drawable.food, "Food 1", "Food Desc", "Detail 1", "Detail 2", "Detail 3", "See Detail"))
//        data.add(ListModel(R.drawable.food, "Food 1", "Food Desc", "Detail 1", "Detail 2", "Detail 3", "See Detail"))
//        data.add(ListModel(R.drawable.food, "Food 1", "Food Desc", "Detail 1", "Detail 2", "Detail 3", "See Detail"))
//        data.add(ListModel(R.drawable.food, "Food 1", "Food Desc", "Detail 1", "Detail 2", "Detail 3", "See Detail"))
//        data.add(ListModel(R.drawable.food, "Food 1", "Food Desc", "Detail 1", "Detail 2", "Detail 3", "See Detail"))
//        data.add(ListModel(R.drawable.food, "Food 1", "Food Desc", "Detail 1", "Detail 2", "Detail 3", "See Detail"))
//
//        adapter = ContentListAdapter(data, object : ContentListAdapter.OnItemClickListener{
//            override fun onClick(data: ListModel) {
//                val intent = Intent(this@ContentListActivity, ContentDetailActivity::class.java)
//                startActivity(intent)
//            }
//
//        })
//    }
}