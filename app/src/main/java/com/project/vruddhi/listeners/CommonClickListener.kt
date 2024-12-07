package com.jetmanagement.listeners

import android.view.View

interface CommonClickListener {
    fun onClick(view : View, text : String? = "")
}