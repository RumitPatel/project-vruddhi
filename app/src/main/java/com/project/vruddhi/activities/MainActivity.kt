package com.project.vruddhi.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.project.vruddhi.BaseActivity
import com.project.vruddhi.R


class MainActivity : BaseActivity() {

    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponents()

    }

    private fun initComponents() {
        mContext = this
        setListeners()
    }

    private fun setListeners() {
        findViewById<Button?>(R.id.btnLogin).setOnClickListener {
            startActivity(Intent(mContext, LoginActivity::class.java))
        }
    }
}