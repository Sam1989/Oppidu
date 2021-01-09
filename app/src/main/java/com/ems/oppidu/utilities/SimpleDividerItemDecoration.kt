package com.ems.oppidu.utilities

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView

class SimpleDividerItemDecoration(drawable: Drawable?) : RecyclerView.ItemDecoration() {

    private val mDivider = drawable

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + mDivider?.intrinsicHeight!!

            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)
        }
    }
}