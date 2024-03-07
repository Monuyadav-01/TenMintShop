package com.example.tenmintshop.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tenmintshop.adapters.AdapterCategory
import com.example.tenmintshop.databinding.FragmentHomeBinding
import com.example.tenmintshop.models.Category
import com.example.tenmintshop.utils.Constants
import com.example.tenmintshop.utils.Utils

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        Utils.setStatusBarColor(requireContext(), requireActivity())

        setAllCategories()
        return binding.root
    }

    private fun setAllCategories() {
        val categoryList = ArrayList<Category>()
        for (i in 0 until Constants.allProductsCategoryIcon.size) {
            categoryList.add(
                Category(
                    Constants.allProductsCategory[i],
                    Constants.allProductsCategoryIcon[i]
                )
            )
        }
        binding.rvCategories.adapter = AdapterCategory(categoryList)
    }

}