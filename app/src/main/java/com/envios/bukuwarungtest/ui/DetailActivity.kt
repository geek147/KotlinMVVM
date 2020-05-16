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

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {


        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}

