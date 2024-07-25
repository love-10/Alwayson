package com.example.alwayson

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HeaderFooterItemDecoration(
    private val headerSpaceHeight: Int,
    private val footerSpaceHeight: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position == RecyclerView.NO_POSITION) {
            return
        }

        val itemCount = state.itemCount

        // Add top margin for the first item
        if (position == 0) {
            outRect.right = headerSpaceHeight
            outRect.left = 100
        } else if (position == itemCount - 1) {
            outRect.left = footerSpaceHeight
        } else {
            outRect.left = 100
        }
    }
}