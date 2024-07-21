package com.project.vruddhi.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.project.vruddhi.R


class LoginActivity : AppCompatActivity() {

    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initComponents()

    }

    private fun initComponents() {
        mContext = this
        setListeners()
    }

    private fun setListeners() {
        findViewById<ImageView?>(R.id.ivBack).setOnClickListener {
            finish()
        }
        findViewById<TextView?>(R.id.tvForgotPassword).setOnClickListener {
            startActivity(Intent(mContext, ResetPasswordActivity::class.java))
        }
    }
}