package com.project.vruddhi.activities

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.project.vruddhi.BaseActivity
import com.project.vruddhi.R
import com.project.vruddhi.databinding.ActivityPregnanatWomanScreeningBinding


class PregnantWomanScreeningActivity : BaseActivity() {

    private lateinit var mContext: Context
    private lateinit var binding: ActivityPregnanatWomanScreeningBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pregnanat_woman_screening)

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
    }
}