package com.example.mynewapplication.adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mynewapplication.kotlinpackage.KotlinModel.Result
import com.example.mynewapplication.R

class ListAdapter(val context: Context): RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private val recipes: MutableList<Result> = mutableListOf()

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val tv_titlecontentlist: TextView = itemView.findViewById(R.id.tv_titlecontentlist)
        val tv_desccontentlist: TextView = itemView.findViewById(R.id.tv_desccontentlist)
        val tv_detail_list1: TextView = itemView.findViewById(R.id.tv_detail_list1)
        val tv_detail_list2: TextView = itemView.findViewById(R.id.tv_detail_list2)
        val tv_detail_list3: TextView = itemView.findViewById(R.id.tv_detail_list3)
        val iv_contentlist: Image = itemView.findViewById(R.id.iv_contentlist)

        fun bind(result: Result){
            tv_titlecontentlist.text = result.title
            tv_desccontentlist.text = result.ingredients
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_recyclerview_contentlist, parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    fun setResult(data: List<Result>){
        recipes.clear()
        recipes.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}