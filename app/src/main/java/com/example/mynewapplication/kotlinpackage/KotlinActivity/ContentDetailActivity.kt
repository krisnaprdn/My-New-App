package com.example.mynewapplication.kotlinpackage.KotlinActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageButton
import com.example.mynewapplication.R
import kotlinx.android.synthetic.main.activity_content_detail.*

class ContentDetailActivity : AppCompatActivity() {

    private lateinit var imageButton: ImageButton
    private lateinit var  webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_detail)

//        ib_back.setOnClickListener {
//            startActivity(Intent(this, ContentListActivity::class.java))
//            finish()
//        }

        wv_detail.settings.javaScriptEnabled = true
        wv_detail.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        wv_detail.loadUrl("href")
    }
}