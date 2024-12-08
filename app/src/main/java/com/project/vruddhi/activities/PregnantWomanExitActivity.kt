package com.project.vruddhi.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.project.vruddhi.BaseActivity
import com.project.vruddhi.R
import com.project.vruddhi.databinding.FragmentPregnanatWomanUpdateCounsellingBinding


class PregnantWomanExitActivity : BaseActivity() {

    private lateinit var mContext: Context
    private lateinit var binding: FragmentPregnanatWomanUpdateCounsellingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(
                this,
                R.layout.fragment_pregnanat_woman_update_counselling
            )

        initComponents()

    }

    private fun initComponents() {
        mContext = this
        setScreeningProgressBar()
        setListeners()
    }

    private fun setListeners() {
        binding.btnNext.setOnClickListener {
            startActivity(
                Intent(
                    mContext,
                    HomeActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
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
                R.color.black
            )
        )
        binding.includeProgress.ivRoundServices.setImageResource(R.drawable.round_green)
        binding.includeProgress.tvExit.setTextColor(
            ContextCompat.getColor(
                mContext,
                R.color.colorPrimary
            )
        )
        binding.includeProgress.ivRoundExit.setImageResource(R.drawable.round_blue)
    }
}