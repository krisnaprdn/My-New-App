package com.example.mynewapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mynewapplication.ContentDetailActivity;
import com.example.mynewapplication.MainActivity;
import com.example.mynewapplication.Model.MainModel;
import com.example.mynewapplication.Model.Results;
import com.example.mynewapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

//    private ArrayList<MainModel> dataList = new ArrayList<>();
    private List<Results> dataList = new ArrayList<>();
    private Context context;

    public MainAdapter(Context context, List<Results> dataList) {
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
//        holder.btn_homepagecontent.setText(dataList.get(position).getBtn_homepagecontent());
        holder.tv_title_homepage.setText(dataList.get(position).getTitle());
        holder.tv_desc_homepage.setText(dataList.get(position).getIngredients());
        holder.btn_homepagecontent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), ContentDetailActivity.class));
            }
        });

        Glide.with(context).load(dataList.get(position).getThumbnail()).into(holder.iv_homepagecontent);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv_homepagecontent;
        private final Button btn_homepagecontent;
        private final TextView tv_title_homepage, tv_desc_homepage;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_homepagecontent = itemView.findViewById(R.id.iv_homepagecontent);
            btn_homepagecontent = itemView.findViewById(R.id.btn_homepagecontent);
            tv_title_homepage = itemView.findViewById(R.id.tv_title_homepage);
            tv_desc_homepage = itemView.findViewById(R.id.tv_desc_homepage);
        }
    }
}
