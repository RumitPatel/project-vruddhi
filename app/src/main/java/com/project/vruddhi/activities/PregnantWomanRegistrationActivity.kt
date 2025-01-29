package com.project.vruddhi.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RadioGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.project.vruddhi.BaseActivity
import com.project.vruddhi.R
import com.project.vruddhi.databinding.FragmentPregnanatWomanRegistrationBinding


class PregnantWomanRegistrationActivity : BaseActivity() {

    private lateinit var mContext: Context
    private lateinit var binding: FragmentPregnanatWomanRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(
                this,
                R.layout.fragment_pregnanat_woman_registration
            )

        initComponents()

    }

    private fun initComponents() {
        mContext = this
        setScreeningProgressBar()
        setListeners()
    }

    private fun setListeners() {
        binding.btnSaveAndNext.setOnClickListener {
            //startActivity(Intent(mContext, PregnantWomanServicesActivity::class.java))
            startActivity(
                Intent(
                    mContext,
                    HomeActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )

        }

        // Add listener to the RadioGroup to enable/disable tvIllnessNote
        binding.rgAnyIllness.setOnCheckedChangeListener { _, checkedId ->
            Log.d("RadioGroup", "Radio button changed: $checkedId")
            when (checkedId) {
                R.id.rbYesAnyIllness -> {
                    // Enable the text box when 'Yes' is selected
                    binding.tvIllnessNote.isEnabled = true
                    binding.tvIllnessNote.hint = ""
                }
                R.id.rbNoAnyIllness -> {
                    // Disable the text box when 'No' is selected
                    binding.tvIllnessNote.isEnabled = false
                    binding.tvIllnessNote.setText("") // Optionally clear the text
                    binding.tvIllnessNote.hint = ""   // Optionally remove the hint
                }
            }
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
                R.color.colorPrimary
            )
        )
        binding.includeProgress.ivRoundRegistration.setImageResource(R.drawable.round_blue)
    }
}