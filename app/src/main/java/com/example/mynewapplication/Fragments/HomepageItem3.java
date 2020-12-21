package com.example.mynewapplication.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mynewapplication.Adapter.MainAdapter;
import com.example.mynewapplication.Model.MainModel;
import com.example.mynewapplication.R;

import java.util.ArrayList;

public class HomepageItem3 extends Fragment {

    private LinearLayoutManager layoutManager;
    private MainAdapter mainAdapter;
    private ArrayList<MainModel> list = new ArrayList<>();
    private MainModel mainModel;
    private RecyclerView recyclerView;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public HomepageItem3() {

    }

    public static HomepageItem3 newInstance(String param1, String param2) {
        HomepageItem3 fragment = new HomepageItem3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage_item3, container, false);
        recyclerView = view.findViewById(R.id.rv_content3);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.rv_content3);
        initData();
        initRecyclerView();
        return view;
    }

    public void initRecyclerView(){
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        mainAdapter = new MainAdapter(list, getActivity());
        recyclerView.setAdapter(mainAdapter);
        mainAdapter.notifyDataSetChanged();
    }

    public void initData(){
        mainModel = new MainModel();
        mainModel.setIv_homepagecontent(R.drawable.food);
        mainModel.setBtn_homepagecontent("Go To Detail");
        list.add(mainModel);

        mainModel = new MainModel();
        mainModel.setIv_homepagecontent(R.drawable.food);
        mainModel.setBtn_homepagecontent("Go To Detail");
        list.add(mainModel);

        mainModel = new MainModel();
        mainModel.setIv_homepagecontent(R.drawable.food);
        mainModel.setBtn_homepagecontent("Go To Detail");
        list.add(mainModel);

        mainModel = new MainModel();
        mainModel.setIv_homepagecontent(R.drawable.food);
        mainModel.setBtn_homepagecontent("Go To Detail");
        list.add(mainModel);

        mainModel = new MainModel();
        mainModel.setIv_homepagecontent(R.drawable.food);
        mainModel.setBtn_homepagecontent("Go To Detail");
        list.add(mainModel);

        mainModel = new MainModel();
        mainModel.setIv_homepagecontent(R.drawable.food);
        mainModel.setBtn_homepagecontent("Go To Detail");
        list.add(mainModel);
    }
}