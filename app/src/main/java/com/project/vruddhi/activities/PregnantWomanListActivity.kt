package com.project.vruddhi.activities

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.project.vruddhi.BaseActivity
import com.project.vruddhi.R
import com.project.vruddhi.adapters.PregnantWomanAdapter
import com.project.vruddhi.databinding.ActivityPregnanatWomanListBinding
import com.project.vruddhi.utils.getTempPatient
import com.project.vruddhi.utils.setVerticalLayoutManager


class PregnantWomanListActivity : BaseActivity() {

    private lateinit var mContext: Context
    private lateinit var binding: ActivityPregnanatWomanListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pregnanat_woman_list)

        initComponents()

    }

    private fun initComponents() {
        mContext = this
        setListeners()

        setVerticalLayoutManager(mContext, binding.rv)

        val pregnantWomanAdapter = PregnantWomanAdapter(getTempPatient()) {
            val patientInfo = it
        }
        binding.rv.adapter = pregnantWomanAdapter
    }

    private fun setListeners() {
        findViewById<ImageView?>(R.id.ivBack).setOnClickListener {
            finish()
        }
    }
}