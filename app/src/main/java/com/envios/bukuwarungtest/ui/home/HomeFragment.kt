package com.envios.bukuwarungtest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.envios.bukuwarungtest.R
import com.envios.bukuwarungtest.databinding.FragmentHomeBinding
import com.envios.bukuwarungtest.utils.Failed
import com.envios.bukuwarungtest.utils.Loading
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    val viewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentHomeBinding

    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
                binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.main = viewModel


        val layoutManager = LinearLayoutManager(context)
        binding.rvUsers.layoutManager = layoutManager
        adapter = context?.let { HomeAdapter(it) }!!
        binding.rvUsers.setHasFixedSize(true)
        adapter.setHasStableIds(true)
        binding.rvUsers.adapter = adapter

        viewModel.getUsers()

        observeLiveData()



        swipe_refresh.setOnRefreshListener {

            viewModel.getUsers()
        }

    }

    private fun observeLiveData(){
        viewModel.states.observe(viewLifecycleOwner, Observer {

            when (it) {
                is HomeViewModel.UsersLoaded -> {
                    adapter.setData(it.userList)
                    adapter.notifyDataSetChanged()
                    swipe_refresh.isRefreshing = false
                }
                is Loading -> {
//                    if (it.isLoading) showLoading() else hideLoading()
//                    rv_users.visibility = if (it.isLoading) View.GONE else View.VISIBLE
                }
                is Failed -> {
                    if (it.error != null) Toast.makeText(requireContext(),it.error!!, Toast.LENGTH_LONG).show()
                    swipe_refresh.isRefreshing = false

                }
            }

        })

    }

    fun showLoading() {
        iv_rotated.visibility = View.VISIBLE
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.rotated_view)
        iv_rotated.startAnimation(animation)
    }

    fun hideLoading() {
        iv_rotated.visibility = View.GONE
        iv_rotated.clearAnimation()

    }


}
