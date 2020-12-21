package com.example.mynewapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynewapplication.Adapter.ContentListAdapter
import com.example.mynewapplication.Model.ListModel

class ContentListActivity : AppCompatActivity() {

    private lateinit var imageButton: ImageButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ContentListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_list)
        imageButton = findViewById(R.id.ib_back)
        imageButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        init()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun init() {
        recyclerView = findViewById(R.id.rv_contentlist)

        val data = ArrayList<ListModel>()
        data.add(ListModel(R.drawable.food, "Food 1", "Food Desc", "Detail 1", "Detail 2", "Detail 3", "See Detail"))
        data.add(ListModel(R.drawable.food, "Food 1", "Food Desc", "Detail 1", "Detail 2", "Detail 3", "See Detail"))
        data.add(ListModel(R.drawable.food, "Food 1", "Food Desc", "Detail 1", "Detail 2", "Detail 3", "See Detail"))
        data.add(ListModel(R.drawable.food, "Food 1", "Food Desc", "Detail 1", "Detail 2", "Detail 3", "See Detail"))
        data.add(ListModel(R.drawable.food, "Food 1", "Food Desc", "Detail 1", "Detail 2", "Detail 3", "See Detail"))
        data.add(ListModel(R.drawable.food, "Food 1", "Food Desc", "Detail 1", "Detail 2", "Detail 3", "See Detail"))
        data.add(ListModel(R.drawable.food, "Food 1", "Food Desc", "Detail 1", "Detail 2", "Detail 3", "See Detail"))
        data.add(ListModel(R.drawable.food, "Food 1", "Food Desc", "Detail 1", "Detail 2", "Detail 3", "See Detail"))
        data.add(ListModel(R.drawable.food, "Food 1", "Food Desc", "Detail 1", "Detail 2", "Detail 3", "See Detail"))
        data.add(ListModel(R.drawable.food, "Food 1", "Food Desc", "Detail 1", "Detail 2", "Detail 3", "See Detail"))
        data.add(ListModel(R.drawable.food, "Food 1", "Food Desc", "Detail 1", "Detail 2", "Detail 3", "See Detail"))

        adapter = ContentListAdapter(data, object : ContentListAdapter.OnItemClickListener{
            override fun onClick(data: ListModel) {
                val intent = Intent(this@ContentListActivity, ContentDetailActivity::class.java)
                startActivity(intent)
            }

        })
//        adapter = ContentListAdapter(data, object : ContentListAdapter.OnAdapterListener{
//            override fun onClick(data: ListModel) {
////                startActivity(Intent(this, ContentDetailActivity::class.java))
//            }
//        })

    }

//    private fun sendDataToDetail(){
//        adapter.setOnclickListener(object : ContentListAdapter.OnItemClickListener{
//            override fun onClick(data: ListModel) {
//                val intent = Intent(this@ContentListActivity, ContentDetailActivity::class.java)
//                startActivity(intent)
//            }
//
//        })
    }