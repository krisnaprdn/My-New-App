package com.example.mynewapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class ContentDetailActivity : AppCompatActivity() {

    private lateinit var imageButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_detail)

        imageButton = findViewById(R.id.ib_back)
        imageButton.setOnClickListener {
//            startActivity(Intent(this, ContentListActivity::class.java))
            finish()
        }
    }
}