package com.project.vruddhi.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.project.vruddhi.BaseActivity
import com.project.vruddhi.R
import com.project.vruddhi.databinding.FragmentPregnanatWomanScreeningBinding


class PregnantWomanScreeningActivity : BaseActivity() {

    private lateinit var mContext: Context
    private lateinit var binding: FragmentPregnanatWomanScreeningBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.fragment_pregnanat_woman_screening)

        initComponents()

    }

    private fun initComponents() {
        mContext = this
        setScreeningProgressBar()
        setListeners()
    }

    private fun setListeners() {

        binding.btnSaveAndNext.setOnClickListener {
            //startActivity(Intent(mContext, PregnantWomanRegistrationActivity::class.java))
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
                R.color.colorPrimary
            )
        )
        binding.includeProgress.ivRoundScreening.setImageResource(R.drawable.round_blue)
    }
}
