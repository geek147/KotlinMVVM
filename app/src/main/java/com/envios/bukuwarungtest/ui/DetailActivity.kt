package com.envios.bukuwarungtest.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.envios.bukuwarungtest.R
import com.envios.bukuwarungtest.data.local.model.User
import com.envios.bukuwarungtest.data.remote.model.Data
import com.envios.bukuwarungtest.utils.BindingConverters
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent: Intent = intent
        val data : User? = intent.getParcelableExtra("data")
        BindingConverters.loadImage(iv_detail_avatar, data?.avatar)
        tv_user_full_name.text = data?.firstName + " " + data?.lastName
        tv_user_email.text = data?.email
    }
}

