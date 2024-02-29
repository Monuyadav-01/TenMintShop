package com.example.tenmintshop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tenmintshop.databinding.ItemViewResourseCatogaryBinding
import com.example.tenmintshop.models.Category

class AdapterCategory(val categoryList: ArrayList<Category>) :
    RecyclerView.Adapter<AdapterCategory.CategoryViewHolder>() {
    inner class CategoryViewHolder(var binding: ItemViewResourseCatogaryBinding) :
        ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemViewResourseCatogaryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.binding.apply {
            ivCategoryImage.setImageResource(category.image)
            tvCategoryTittle.text = category.tittle
        }
    }
}