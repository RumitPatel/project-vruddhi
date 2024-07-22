package com.project.vruddhi.utils

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

object Utils {

}

fun View.setBGDrawable(context: Context, resourceId: Int) {
    this.background = ContextCompat.getDrawable(context, resourceId)

}

fun setVerticalLayoutManager(context: Context, recyclerView: RecyclerView) {
    val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    recyclerView.layoutManager = linearLayoutManager
}

fun setGridLayoutManager(context: Context, recyclerView: RecyclerView, count: Int) {
    recyclerView.layoutManager = GridLayoutManager(context, count)
}

fun setHorizontalLayoutManager(context: Context, recyclerView: RecyclerView) {
    val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    recyclerView.layoutManager = linearLayoutManager
}