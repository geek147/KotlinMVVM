package com.envios.bukuwarungtest.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.envios.bukuwarungtest.R
import com.envios.bukuwarungtest.model.Data

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent: Intent = intent
        val data : Data? = intent.getParcelableExtra("data")
    }
}

