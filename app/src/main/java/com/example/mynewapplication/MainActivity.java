package com.example.mynewapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mynewapplication.Adapter.MainAdapter;
import com.example.mynewapplication.Model.MainModel;
import com.example.mynewapplication.Model.Response;
import com.example.mynewapplication.Model.Results;
import com.example.mynewapplication.ViewModel.MainActivityVM;
import com.example.mynewapplication.ViewModel.MainActivityVMF;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    public ImageButton ib_list;
    public MainActivityVM viewModel;
    public MainActivityVMF viewModelFactory;
    private List<Results> results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModelFactory = new MainActivityVMF(this);
        viewModel = ViewModelProviders.of(MainActivity.this, viewModelFactory).get(MainActivityVM.class);

        ib_list = findViewById(R.id.ib_list);

        ib_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContentListActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initLiveData(){
        viewModel.getResults().observe(this, new Observer<Response>() {
            @Override
            public void onChanged(Response response) {
                if (response != null){
                    if (!results.isEmpty()){
                        results.clear();
                    }
                    results = response.getResults();
                }
            }
        });
    }

}