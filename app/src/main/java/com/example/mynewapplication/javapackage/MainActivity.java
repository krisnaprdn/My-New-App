package com.example.mynewapplication.javapackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.mynewapplication.R;
import com.example.mynewapplication.javapackage.adapter.MainAdapter;
import com.example.mynewapplication.javapackage.model.ResponseModel;
import com.example.mynewapplication.javapackage.model.MainModel;
import com.example.mynewapplication.javapackage.model.Results;
import com.example.mynewapplication.javapackage.viewmodel.MainActivityVM;
import com.example.mynewapplication.javapackage.viewmodel.MainActivityVMF;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String URL_JSON = "http://www.recipepuppy.com/api/";
    private JsonArrayRequest arrayRequest;
    private RequestQueue requestQueue;
    public ImageButton ib_list, ib_go_to;
    public MainActivityVM viewModel;
    public MainActivityVMF viewModelFactory;
    private List<Results> resultsData = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private MainAdapter mainAdapter;
    private ArrayList<MainModel> list = new ArrayList<>();
    private MainModel mainModel;
    private RecyclerView recyclerView;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModelFactory = new MainActivityVMF(this);
        viewModel = ViewModelProviders.of(MainActivity.this, viewModelFactory).get(MainActivityVM.class);

        ib_list = findViewById(R.id.ib_list);
        ib_go_to = findViewById(R.id.ib_go_to);
        recyclerView = findViewById(R.id.rv_homepage_content);
        relativeLayout = findViewById(R.id.rl_content_gotolist);


        ib_go_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ContentListActivity.class);
                startActivity(i);
            }
        });

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ContentListActivity.class);
                startActivity(i);
            }
        });

        ib_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ContentListActivity.class);
                startActivity(i);
            }
        });

        initLiveData();
        initRecyclerView();
        viewModel.fetchRecipe(this);
    }

//    public void initLiveData(){
//        arrayRequest = new JsonArrayRequest(URL_JSON, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                JSONObject jsonObject = null;
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        jsonObject = response.getJSONObject(i);
//                        Results results = new Results();
//                        results.setTitle(jsonObject.getString("title"));
//                        results.setThumbnail(jsonObject.getString("thumbnail"));
//                        results.setIngredients(jsonObject.getString("ingredients"));
//                        resultsData.add(results);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                initRecyclerView(resultsData);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//        requestQueue = Volley.newRequestQueue(MainActivity.this);
//        requestQueue.add(arrayRequest);
//    }

    public void initLiveData(){
        viewModel.getResults().observe(this, new Observer<ResponseModel>() {
            @Override
            public void onChanged(ResponseModel responseModel) {
                if (responseModel != null){
                    if (!resultsData.isEmpty()){
                        resultsData.clear();
                    }
                    resultsData = responseModel.getResults();
                    MainAdapter mainAdapter = new MainAdapter(MainActivity.this, resultsData);
                    recyclerView.setAdapter(mainAdapter);
                    mainAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(MainActivity.this, "No Result", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void initRecyclerView(){
        layoutManager = new LinearLayoutManager(MainActivity.this, recyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        MainAdapter mainAdapter = new MainAdapter(MainActivity.this, resultsData);
        recyclerView.setAdapter(mainAdapter);
        mainAdapter.notifyDataSetChanged();
    }
}
