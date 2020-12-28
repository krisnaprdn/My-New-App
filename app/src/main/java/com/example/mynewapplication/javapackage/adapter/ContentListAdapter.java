package com.example.mynewapplication.javapackage.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.mynewapplication.R;
import com.example.mynewapplication.javapackage.ContentDetailActivity;
import com.example.mynewapplication.javapackage.model.Results;
import java.util.ArrayList;
import java.util.List;

public class ContentListAdapter extends RecyclerView.Adapter<ContentListAdapter.ContentListViewHolder> {

    private List<Results> dataList = new ArrayList<>();
    private Context context;

    public ContentListAdapter(Context context, List<Results> dataList){
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ContentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_recyclerview_contentlist, parent, false);

        return new ContentListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentListViewHolder holder, int position) {

        final Results model = dataList.get(position);

        holder.tv_titlecontentlist.setText(dataList.get(position).getTitle());
        holder.tv_desccontentlist.setText(dataList.get(position).getIngredients());

        Glide.with(context)
                .load(dataList.get(position).getThumbnail())
                .into(holder.iv_contentlist);

        holder.btn_contentlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(holder.itemView.getContext(), ContentDetailActivity.class);
                i.putExtra("href", model.getHref());
                holder.itemView.getContext().startActivity(i);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(holder.itemView.getContext(), ContentDetailActivity.class);
                i.putExtra("href", model.getHref());
                holder.itemView.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ContentListViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv_contentlist;
        private final TextView tv_titlecontentlist, tv_desccontentlist;
        private final Button btn_contentlist;

        public ContentListViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_contentlist = itemView.findViewById(R.id.iv_contentlist);
            tv_titlecontentlist = itemView.findViewById(R.id.tv_titlecontentlist);
            tv_desccontentlist = itemView.findViewById(R.id.tv_desccontentlist);
            btn_contentlist = itemView.findViewById(R.id.btn_contentlist);

        }
    }
}
