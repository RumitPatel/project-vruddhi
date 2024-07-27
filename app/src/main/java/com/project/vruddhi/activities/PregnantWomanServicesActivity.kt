package com.project.vruddhi.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.project.vruddhi.BaseActivity
import com.project.vruddhi.R
import com.project.vruddhi.databinding.ActivityPregnanatWomanServicesBinding


class PregnantWomanServicesActivity : BaseActivity() {

    private lateinit var mContext: Context
    private lateinit var binding: ActivityPregnanatWomanServicesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_pregnanat_woman_services)

        initComponents()

    }

    private fun initComponents() {
        mContext = this
        setScreeningProgressBar()
        setListeners()
    }

    private fun setListeners() {
        binding.ivBack.setOnClickListener {
            finish()
        }
        binding.btnNext.setOnClickListener {
            startActivity(Intent(mContext, PregnantWomanExitActivity::class.java))
        }
    }

    private fun setScreeningProgressBar() {
        binding.includeProgress.tvScreening.setTextColor(
            ContextCompat.getColor(
                mContext,
                R.color.black
            )
        )
        binding.includeProgress.ivRoundScreening.setImageResource(R.drawable.round_green)
        binding.includeProgress.tvRegistration.setTextColor(
            ContextCompat.getColor(
                mContext,
                R.color.black
            )
        )
        binding.includeProgress.ivRoundRegistration.setImageResource(R.drawable.round_green)
        binding.includeProgress.tvServices.setTextColor(
            ContextCompat.getColor(
                mContext,
                R.color.colorPrimary
            )
        )
        binding.includeProgress.ivRoundServices.setImageResource(R.drawable.round_blue)
    }
}