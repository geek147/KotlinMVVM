package com.envios.bukuwarungtest.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.envios.bukuwarungtest.R
import com.envios.bukuwarungtest.data.local.model.User
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent: Intent = intent
        val data : User? = intent.getParcelableExtra("data")

        tv_user_full_name.text = data?.firstName + " " + data?.lastName
        tv_user_email.text = data?.email
        Glide.with(this)
            .load(data?.avatar)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background))
            .into(iv_detail_avatar)

        supportActionBar?.setTitle(R.string.user_profile)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

