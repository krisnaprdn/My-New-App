package com.example.mynewapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynewapplication.Model.MainModel;
import com.example.mynewapplication.R;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private ArrayList<MainModel> dataList = new ArrayList<>();
    private Context context;

    public MainAdapter(ArrayList<MainModel> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_recyclerview_homepagecontent, parent, false);

        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.iv_homepagecontent.setImageResource(dataList.get(position).getIv_homepagecontent());
        holder.btn_homepagecontent.setText(dataList.get(position).getBtn_homepagecontent());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class MainViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv_homepagecontent;
        private final Button btn_homepagecontent;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_homepagecontent = itemView.findViewById(R.id.iv_homepagecontent);
            btn_homepagecontent = itemView.findViewById(R.id.btn_homepagecontent);
        }
    }
}
