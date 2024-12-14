package com.project.vruddhi.utils

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

object Utils {

    /**
     * Method to get GSON object
     */
    fun getGsonObject() = Gson()

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

fun String?.isNotNullOrBlank(): Boolean {
    return this != null
            && !TextUtils.isEmpty(this)
            && !this.equals("", true)
            && !this.equals("null", true)
            && !this.equals("0000-00-00", true)
}

fun TextView.checkNullAndSet(text: String?) {
    this.text = if (text.isNotNullOrBlank()) text else ""
}
