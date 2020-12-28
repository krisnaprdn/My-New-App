package com.example.mynewapplication.kotlinpackage.features.presentation.recipes

import com.example.mynewapplication.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

class LoadingItem : Item() {

    override fun getLayout(): Int = R.layout.layout_loader

    override fun bind(viewHolder: ViewHolder, position: Int) {}
}
