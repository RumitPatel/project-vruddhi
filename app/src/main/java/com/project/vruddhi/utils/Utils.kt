package com.project.vruddhi.utils

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat

object Utils {

}

fun View.setBGDrawable(context: Context, resourceId: Int) {
    this.background = ContextCompat.getDrawable(context, resourceId)

}