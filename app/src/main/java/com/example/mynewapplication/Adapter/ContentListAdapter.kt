package com.example.mynewapplication.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mynewapplication.KotlinModel.ListModel
import com.example.mynewapplication.R

class ContentListAdapter (private val data: ArrayList<ListModel>,
                          private var onItemClickListener: OnItemClickListener): RecyclerView.Adapter<ContentListAdapter.ContentListViewHolder>() {

    private var btnContent: Button? = null

    fun setOnclickListener(listener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentListViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        return ContentListViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ContentListViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener {
            onItemClickListener.onClick(data[position]) }
        btnContent?.setOnClickListener { onItemClickListener.onClick(data[position]) }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ContentListViewHolder(inflater: LayoutInflater, parent: ViewGroup):
            RecyclerView.ViewHolder(inflater.inflate(R.layout.layout_recyclerview_contentlist, parent, false)) {

        private var imgView: ImageView? = null
        private var txtTitle: TextView? = null
        private var txtDesc: TextView? = null
        private var txtDetail1: TextView? = null
        private var txtDetail2: TextView? = null
        private var txtDetail3: TextView? = null
        private var btnContent: Button? = null

        init {
            imgView = itemView.findViewById(R.id.iv_contentlist)
            txtTitle = itemView.findViewById(R.id.tv_titlecontentlist)
            txtDesc = itemView.findViewById(R.id.tv_desccontentlist)
            txtDetail1 = itemView.findViewById(R.id.tv_detail_list1)
            txtDetail2 = itemView.findViewById(R.id.tv_detail_list2)
            txtDetail3 = itemView.findViewById(R.id.tv_detail_list3)
            btnContent = itemView.findViewById(R.id.btn_contentlist)
        }

        fun bind(data: ListModel){
            imgView?.setImageResource(data.imgView)
            txtTitle?.text = data.txtTitle
            txtDesc?.text = data.txtDesc
            txtDetail1?.text = data.txtDetail1
            txtDetail2?.text = data.txtDetail2
            txtDetail3?.text = data.txtDetail3
            btnContent?.text = data.btnContent
        }
    }

    interface OnItemClickListener{
        fun onClick(data: ListModel)
    }
}