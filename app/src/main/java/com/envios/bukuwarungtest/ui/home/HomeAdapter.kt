package com.envios.bukuwarungtest.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.envios.bukuwarungtest.R
import com.envios.bukuwarungtest.databinding.AdapterHomeBinding
import com.envios.bukuwarungtest.model.Data
import com.envios.bukuwarungtest.ui.DetailActivity

class HomeAdapter(context: Context): RecyclerView.Adapter<HomeAdapter.MainViewHolder>(){
    private var listData: MutableList<Data?>? = mutableListOf()
    private var context = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MainViewHolder{
        val binding: AdapterHomeBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.adapter_home, parent, false)
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (listData.isNullOrEmpty()) {
            0
        } else{
            listData!!.size
        }
    }

    override fun getItemId(position: Int): Long {
        val data: Data? = listData?.get(position)
        return data!!.id!!.toLong()
    }

    fun setData(list: List<Data?>?){
        if (list != null) {
            this.listData?.clear()
            this.listData?.addAll(list)
        }
    }



    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        listData?.get(holder.adapterPosition)?.let {
            holder.bindData(it, context )
        }
    }



    class MainViewHolder(private val binding: AdapterHomeBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindData(model: Data, context: Context){
            val viewModel = HomeAdapterViewModel(model)
            binding.itemData = viewModel
            itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("data", model)
                context.startActivity(intent)
            }
        }



    }


}