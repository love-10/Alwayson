package com.example.alwayson

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alwayson.databinding.ItemListViewBinding

class ItemListView(context: Context, attributeSet: AttributeSet) :
    FrameLayout(context, attributeSet) {
    private val binding: ItemListViewBinding =
        ItemListViewBinding.inflate(LayoutInflater.from(context))
    private val itemAdapter = ItemAdapter().apply {
        animationEnable = true
        val data = mutableListOf<Bean>()
        (0 until 100).forEach { i ->
            data.add(Bean(name = "$i"))
        }
        submitList(data)
    }

    init {
        addView(binding.root)
        binding.rec.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = itemAdapter
        }

    }
}