package com.project.vruddhi.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import com.project.vruddhi.BaseActivity
import com.project.vruddhi.R


class HomeActivity : BaseActivity() {

    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initComponents()

    }

    private fun initComponents() {
        mContext = this
        setListeners()
    }

    private fun setListeners() {
        findViewById<LinearLayout?>(R.id.llPregnantWoman).setOnClickListener {
            startActivity(Intent(mContext, PregnantWomanListActivity::class.java))
        }
    }
}