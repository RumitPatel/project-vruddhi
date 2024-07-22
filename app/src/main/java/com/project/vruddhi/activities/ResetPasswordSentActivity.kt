package com.project.vruddhi.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.project.vruddhi.BaseActivity
import com.project.vruddhi.R


class ResetPasswordSentActivity : BaseActivity() {

    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password_sent)

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

        findViewById<Button?>(R.id.btnBackToLogin).setOnClickListener {
            startActivity(
                Intent(
                    mContext,
                    MainActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }
    }
}