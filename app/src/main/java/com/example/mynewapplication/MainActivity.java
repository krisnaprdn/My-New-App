package com.example.mynewapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mynewapplication.kotlinpackage.MainAdapter;
import com.example.mynewapplication.kotlinpackage.core.platform.BaseFragment;
import com.example.mynewapplication.kotlinpackage.features.presentation.MainActivityKotlin;
import com.example.mynewapplication.kotlinpackage.features.presentation.recipes.RecipesFragment;
import com.example.mynewapplication.model.MainModel;
import com.example.mynewapplication.model.Results;
import com.example.mynewapplication.viewmodel.MainActivityVM;
import com.example.mynewapplication.viewmodel.MainActivityVMF;
import com.example.mynewapplication.kotlinpackage.KotlinActivity.ContentListActivity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private String URL_JSON = "http://www.recipepuppy.com/api/";
    private JsonArrayRequest arrayRequest;
    private RequestQueue requestQueue;
    public ImageButton ib_list;
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
        setContentView(R.layout.activity_main);
        viewModelFactory = new MainActivityVMF(this);
        viewModel = ViewModelProviders.of(MainActivity.this, viewModelFactory).get(MainActivityVM.class);

        ib_list = findViewById(R.id.ib_list);
        recyclerView = findViewById(R.id.rv_homepage_content);


        ib_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainActivityKotlin.class);
                startActivity(i);
            }
        });

        initLiveData();
        initRecyclerView(resultsData);
        viewModel.fetchRecipe(this);
    }

    public void initLiveData(){
        arrayRequest = new JsonArrayRequest(URL_JSON, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        Results results = new Results();
                        results.setTitle(jsonObject.getString("title"));
                        results.setThumbnail(jsonObject.getString("thumbnail"));
                        results.setIngredients(jsonObject.getString("ingredients"));
                        resultsData.add(results);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                initRecyclerView(resultsData);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(arrayRequest);
    }

    public void initRecyclerView(List<Results> resultsData){
        layoutManager = new LinearLayoutManager(MainActivity.this, recyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        MainAdapter mainAdapter = new MainAdapter(MainActivity.this, resultsData);
        recyclerView.setAdapter(mainAdapter);
        mainAdapter.notifyDataSetChanged();
    }
}
