package com.example.tenmintshop.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tenmintshop.R
import com.example.tenmintshop.databinding.FragmentHomeBinding
import com.example.tenmintshop.utils.Utils

class HomeFragment : Fragment() {

private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentHomeBinding.inflate(inflater, container , false)

        Utils.setStatusBarColor(requireContext() , requireActivity())
        return  binding.root
    }

}