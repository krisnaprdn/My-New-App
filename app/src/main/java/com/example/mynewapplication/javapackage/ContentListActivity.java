package com.example.mynewapplication.javapackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mynewapplication.javapackage.adapter.ContentListAdapter;
import com.example.mynewapplication.javapackage.adapter.MainAdapter;
import com.example.mynewapplication.javapackage.model.MainModel;
import com.example.mynewapplication.javapackage.model.ResponseModel;
import com.example.mynewapplication.javapackage.model.Results;
import com.example.mynewapplication.javapackage.viewmodel.MainActivityVM;
import com.example.mynewapplication.javapackage.viewmodel.MainActivityVMF;
import java.util.ArrayList;
import java.util.List;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.mynewapplication.R;



public class ContentListActivity extends AppCompatActivity {

    private String URL_JSON = "http://www.recipepuppy.com/api/";
    private JsonArrayRequest arrayRequest;
    private RequestQueue requestQueue;
    public ImageButton ib_back;
    public MainActivityVM viewModel;
    public MainActivityVMF viewModelFactory;
    private List<Results> resultsData = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private MainAdapter mainAdapter;
    private ArrayList<MainModel> list = new ArrayList<>();
    private MainModel mainModel;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_list);
        viewModelFactory = new MainActivityVMF(this);
        viewModel = ViewModelProviders.of(ContentListActivity.this, viewModelFactory).get(MainActivityVM.class);

        ib_back = findViewById(R.id.ib_back);
        recyclerView = findViewById(R.id.rv_contentlist);

        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        initLiveData();
        initRecyclerView();
        viewModel.fetchRecipe(this);
    }

    public void initLiveData(){
        viewModel.getResults().observe(this, new Observer<ResponseModel>() {
            @Override
            public void onChanged(ResponseModel responseModel) {
                if (responseModel != null){
                    if (!resultsData.isEmpty()){
                        resultsData.clear();
                    }
                    resultsData = responseModel.getResults();
                    ContentListAdapter adapter = new ContentListAdapter(ContentListActivity.this, resultsData);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(ContentListActivity.this, "No Result", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void initRecyclerView(){
        layoutManager = new LinearLayoutManager(ContentListActivity.this, recyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        ContentListAdapter adapter = new ContentListAdapter(ContentListActivity.this, resultsData);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

//    public void initLiveData(){
//
//    }
}