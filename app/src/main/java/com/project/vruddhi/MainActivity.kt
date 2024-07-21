package com.project.vruddhi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.project.vruddhi.activities.LoginActivity


class MainActivity : AppCompatActivity() {

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