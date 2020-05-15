package com.envios.bukuwarungtest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.envios.bukuwarungtest.R
import com.envios.bukuwarungtest.databinding.FragmentHomeBinding
import com.envios.bukuwarungtest.utils.NetworkStateUtil

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.main = viewModel


        val layoutManager = LinearLayoutManager(context)
        binding.rvUsers.layoutManager = layoutManager
        adapter = context?.let { HomeAdapter(it) }!!
        binding.rvUsers.setHasFixedSize(true)
        adapter.setHasStableIds(true)
        binding.rvUsers.adapter = adapter

        viewModel.searchUsers()

        searchUser()

    }

    private fun observeLiveData(){
        viewModel.listUser.observe(viewLifecycleOwner, Observer {

            adapter.setData(it?.data!!)
            adapter.notifyDataSetChanged()
        })
        viewModel.error.observe(viewLifecycleOwner, Observer {

            Toast.makeText(requireContext(),"There is a problem with getting data from server",
                Toast.LENGTH_SHORT).show()

        })
    }

    private fun searchUser() {
        val networkStateUtil = NetworkStateUtil(requireContext())

        networkStateUtil.observe(viewLifecycleOwner, Observer{
                isConnected ->
            isConnected?.let {
                    observeLiveData()
            }
        } )
    }
}
