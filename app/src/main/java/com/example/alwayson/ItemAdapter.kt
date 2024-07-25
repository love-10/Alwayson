package com.example.alwayson

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter4.BaseQuickAdapter
import com.example.alwayson.databinding.ItemIconBinding

class ItemAdapter : BaseQuickAdapter<Bean, ItemAdapter.VH>() {

    // 自定义ViewHolder类
    class VH(
        parent: ViewGroup,
        val binding: ItemIconBinding = ItemIconBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ),
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        // 返回一个 ViewHolder
        return VH(parent)
    }

    override fun onBindViewHolder(holder: VH, position: Int, item: Bean?) {
        // 设置item数据
        if (item == null) {
            return
        }
        val binding = holder.binding
        binding.img.setImageBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.head))
        binding.name.text = item.name
        binding.root.setBackgroundColor(
            if (position % 2 == 0) Color.parseColor("#6BE91E63") else Color.parseColor(
                "#86FF5722"
            )
        )
    }

}