package com.example.alwayson

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
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
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, true)
            adapter = itemAdapter
            addItemDecoration(HeaderFooterItemDecoration(500, 500))
        }
        binding.rec.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val centerView = findCenterView(recyclerView)
                centerView?.let {
                    val position = recyclerView.getChildAdapterPosition(it)
                    // 在这里处理位于屏幕中间的item
                    Log.d(
                        "xxxxx",
                        "中间的item位置: ${(itemAdapter.getItem(position) as Bean).name}"
                    )
                }
            }
        })
    }

    fun findCenterView(recyclerView: RecyclerView): View? {
        val layoutManager = recyclerView.layoutManager as? LinearLayoutManager ?: return null
        val childCount = recyclerView.childCount
        if (childCount == 0) {
            return null
        }

        val centerPosition = recyclerView.width / 2
        var centerView: View? = null
        var minDistance = Int.MAX_VALUE

        for (i in 0 until childCount) {
            val child = recyclerView.getChildAt(i)
            val childCenter = (child.right + child.left) / 2
            val distance = kotlin.math.abs(childCenter - centerPosition)

            if (distance < minDistance) {
                minDistance = distance
                centerView = child
            }
        }
        return centerView
    }
}