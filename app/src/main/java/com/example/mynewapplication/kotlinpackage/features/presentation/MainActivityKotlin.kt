package com.example.mynewapplication.kotlinpackage.features.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.example.mynewapplication.R
import kotlinx.android.synthetic.main.activity_content_list.*
import kotlinx.android.synthetic.main.activity_kotlinmain.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivityKotlin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlinmain)
        setSupportActionBar(toolbar)
        setupActionBar()

        ib_back.setOnClickListener {
            finish()
        }
    }

    private fun setupActionBar() {
        val navController = findNavController(this, R.id.nav_host_fragment)
        setupActionBarWithNavController(this, navController)
    }
}
