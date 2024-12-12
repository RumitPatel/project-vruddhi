package com.project.vruddhi.ui.pregnantwoman

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.project.vruddhi.R
import com.project.vruddhi.base.FragmentBase
import com.project.vruddhi.databinding.FragmentPregnanatWomanServicesBinding
import com.project.vruddhi.extensions.setTitle
import com.project.vruddhi.network.ResponseHandler
import com.project.vruddhi.ui.pregnantwoman.model.request.PregnantWomanUpdateCounsellingRequest
import com.project.vruddhi.ui.pregnantwoman.viewmodel.PregnantWomanViewModel

/**
 * Pregnant Woman screening class
 *
 */
class PregnantWomanServicesFragment : FragmentBase() {

    private lateinit var _binding: FragmentPregnanatWomanServicesBinding
    private val binding get() = _binding
    private var mView: View? = null

    private val viewModel: PregnantWomanViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        if (mView == null) {
            _binding =
                FragmentPregnanatWomanServicesBinding.inflate(inflater, container, false)
            mView = binding.root
        }
        return mView
    }

    override fun setupToolbar() {
        setTitle(getString(R.string.pregnant_woman))
    }

    override fun initializeScreenVariables() {
        viewModel.init()
        setScreeningProgressBar()
        setListeners()
        setDataObservers()
    }

    override fun makeApiCalls() {
        viewModel.mPregnantWomanGetScreeningInfo?.screeningId?.let {
            viewModel.callPregnantWomanGetCounsellingApi(it)
        }
    }

    private fun setScreeningProgressBar() {
        binding.includeProgress.tvScreening.setTextColor(
            ContextCompat.getColor(
                requireActivity(),
                R.color.black
            )
        )
        binding.includeProgress.ivRoundScreening.setImageResource(R.drawable.round_green)
        binding.includeProgress.tvRegistration.setTextColor(
            ContextCompat.getColor(
                requireActivity(),
                R.color.black
            )
        )
        binding.includeProgress.ivRoundRegistration.setImageResource(R.drawable.round_green)
        binding.includeProgress.tvServices.setTextColor(
            ContextCompat.getColor(
                requireActivity(),
                R.color.colorPrimary
            )
        )
        binding.includeProgress.ivRoundServices.setImageResource(R.drawable.round_blue)
    }

    /**
     * Method to set click listener
     */
    private fun setListeners() {
        binding.btnNext.setOnClickListener {

            val list = ArrayList<PregnantWomanUpdateCounsellingRequest>()

            val data1 = PregnantWomanUpdateCounsellingRequest()
            data1.counsNo = 1
            data1.weight = binding.tvWeight1.text.toString()
            data1.height = binding.tvHeight1.text.toString()
            data1.hemoglobinLevel = binding.tvHemoglobinLevel1.text.toString()
            data1.isNutritionKitGiven = if (binding.cbNutritionKitGiven.isChecked) 1 else 0
            data1.isSoapGiven = if (binding.cbHandWashingSoapGiven.isChecked) 1 else 0
            data1.isDietCounselled =
                if (binding.cbCounselledAboutDietDuringPregnancy.isChecked) 1 else 0
            data1.isHandwashCounselled =
                if (binding.cbCounselledAboutHandWashAndHygiene.isChecked) 1 else 0
            data1.isAnaemiaCounselled =
                if (binding.counselledAboutTreatmentForAnemia.isChecked) 1 else 0
            data1.complications = binding.tvComplicationNote.text.toString()
            data1.photo = ""

            val data2 = PregnantWomanUpdateCounsellingRequest()
            data2.counsNo = 2
            data2.weight = binding.tvWeight2.text.toString()
            data2.height = binding.tvHeight2.text.toString()
            data2.hemoglobinLevel = binding.tvHemoglobinLevel2.text.toString()
            data2.isNutritionKitGiven = if (binding.cbNutritionKitGiven2.isChecked) 1 else 0
            data2.isSoapGiven = if (binding.cbHandWashingSoapGiven2.isChecked) 1 else 0
            data2.isDietCounselled =
                if (binding.cbCounselledAboutDietDuringPregnancy2.isChecked) 1 else 0
            data2.isHandwashCounselled =
                if (binding.cbCounselledAboutHandWashAndHygiene2.isChecked) 1 else 0
            data2.isAnaemiaCounselled =
                if (binding.counselledAboutTreatmentForAnemia2.isChecked) 1 else 0
            data2.complications = binding.tvComplicationNote2.text.toString()
            data2.photo = ""

            val data3 = PregnantWomanUpdateCounsellingRequest()
            data3.counsNo = 3
            data3.weight = binding.tvWeight3.text.toString()
            data3.height = binding.tvHeight3.text.toString()
            data3.hemoglobinLevel = binding.tvHemoglobinLevel3.text.toString()
            data3.isNutritionKitGiven = if (binding.cbNutritionKitGiven3.isChecked) 1 else 0
            data3.isSoapGiven = if (binding.cbHandWashingSoapGiven3.isChecked) 1 else 0
            data3.isDietCounselled =
                if (binding.cbCounselledAboutDietDuringPregnancy3.isChecked) 1 else 0
            data3.isHandwashCounselled =
                if (binding.cbCounselledAboutHandWashAndHygiene3.isChecked) 1 else 0
            data3.isAnaemiaCounselled =
                if (binding.counselledAboutTreatmentForAnemia3.isChecked) 1 else 0
            data3.complications = binding.tvComplicationNote3.text.toString()
            data3.photo = ""

            list.add(data1)
            list.add(data2)
            list.add(data3)

            viewModel.mPregnantWomanGetScreeningInfo?.screeningId?.let { it1 ->
                viewModel.callPregnantWomanUpdateServicesApi(
                    it1,
                    list
                )
            }
        }

        binding.tvScreening1.isSelected = false
        binding.llScreening1.visibility = View.GONE

        binding.tvScreening2.isSelected = false
        binding.llScreening2.visibility = View.GONE

        binding.tvScreening3.isSelected = false
        binding.llScreening3.visibility = View.GONE

        binding.tvScreening1.setOnClickListener {
            if (it.isSelected) {
                it.isSelected = false
                binding.llScreening1.visibility = View.GONE
            } else {
                it.isSelected = true
                binding.llScreening1.visibility = View.VISIBLE
            }
        }

        binding.tvScreening2.setOnClickListener {
            if (it.isSelected) {
                it.isSelected = false
                binding.llScreening2.visibility = View.GONE
            } else {
                it.isSelected = true
                binding.llScreening2.visibility = View.VISIBLE
            }
        }

        binding.tvScreening3.setOnClickListener {
            if (it.isSelected) {
                it.isSelected = false
                binding.llScreening3.visibility = View.GONE
            } else {
                it.isSelected = true
                binding.llScreening3.visibility = View.VISIBLE
            }
        }
    }

    /**
     * Method to set data observers
     */
    private fun setDataObservers() {
        viewModel.apply {

            pregnantWomanGetCounsellingResponse.observe(viewLifecycleOwner) {
                when (it) {
                    is ResponseHandler.Loading -> {
                        showProgressBar()
                    }

                    is ResponseHandler.OnSuccessResponse -> {
                        hideProgressBar()

                        it.response?.data?.let {
                            if (it.isNotEmpty()) {

                                for (i in it) {
                                    if (i.counsNo == 1) {
                                        val data = i

                                        binding.tvWeight1.setText(data.weight.toString())
                                        binding.tvHeight1.setText(data.height.toString())
                                        binding.tvHemoglobinLevel1.setText(data.hemoglobinLevel.toString())
                                        binding.cbNutritionKitGiven.isChecked =
                                            if (data.isNutritionKitGiven == 1) true else false
                                        binding.cbHandWashingSoapGiven.isChecked =
                                            if (data.isSoapGiven == 1) true else false
                                        binding.cbHandWashingSoapGiven.isChecked =
                                            if (data.isSoapGiven == 1) true else false
                                        binding.cbCounselledAboutDietDuringPregnancy.isChecked =
                                            if (data.isDietCounselled == 1) true else false
                                        binding.cbCounselledAboutHandWashAndHygiene.isChecked =
                                            if (data.isHandwashCounselled == 1) true else false
                                        binding.counselledAboutTreatmentForAnemia.isChecked =
                                            if (data.isAnaemiaCounselled == 1) true else false
                                        binding.tvComplicationNote.setText(data.complications)
                                    }
                                    if (i.counsNo == 2) {
                                        val data = i

                                        binding.tvWeight2.setText(data.weight.toString())
                                        binding.tvHeight2.setText(data.height.toString())
                                        binding.tvHemoglobinLevel2.setText(data.hemoglobinLevel.toString())
                                        binding.cbNutritionKitGiven2.isChecked =
                                            if (data.isNutritionKitGiven == 1) true else false
                                        binding.cbHandWashingSoapGiven2.isChecked =
                                            if (data.isSoapGiven == 1) true else false
                                        binding.cbHandWashingSoapGiven2.isChecked =
                                            if (data.isSoapGiven == 1) true else false
                                        binding.cbCounselledAboutDietDuringPregnancy2.isChecked =
                                            if (data.isDietCounselled == 1) true else false
                                        binding.cbCounselledAboutHandWashAndHygiene2.isChecked =
                                            if (data.isHandwashCounselled == 1) true else false
                                        binding.counselledAboutTreatmentForAnemia2.isChecked =
                                            if (data.isAnaemiaCounselled == 1) true else false
                                        binding.tvComplicationNote2.setText(data.complications)
                                    }
                                    if (i.counsNo == 3) {
                                        val data = i

                                        binding.tvWeight3.setText(data.weight.toString())
                                        binding.tvHeight3.setText(data.height.toString())
                                        binding.tvHemoglobinLevel3.setText(data.hemoglobinLevel.toString())
                                        binding.cbNutritionKitGiven3.isChecked =
                                            if (data.isNutritionKitGiven == 1) true else false
                                        binding.cbHandWashingSoapGiven3.isChecked =
                                            if (data.isSoapGiven == 1) true else false
                                        binding.cbHandWashingSoapGiven3.isChecked =
                                            if (data.isSoapGiven == 1) true else false
                                        binding.cbCounselledAboutDietDuringPregnancy3.isChecked =
                                            if (data.isDietCounselled == 1) true else false
                                        binding.cbCounselledAboutHandWashAndHygiene3.isChecked =
                                            if (data.isHandwashCounselled == 1) true else false
                                        binding.counselledAboutTreatmentForAnemia3.isChecked =
                                            if (data.isAnaemiaCounselled == 1) true else false
                                        binding.tvComplicationNote3.setText(data.complications)
                                    }
                                }
                            }
                        }
                    }

                    is ResponseHandler.OnFailed -> {
                        hideProgressBar()
                        handleError(it.code ?: 0, it.message)
                    }

                    else -> Unit
                }
            }

            pregnantWomanServicesResponse.observe(viewLifecycleOwner) {
                when (it) {
                    is ResponseHandler.Loading -> {
                        showProgressBar()
                    }

                    is ResponseHandler.OnSuccessResponse -> {
                        hideProgressBar()

                        showSnackBar(it.response?.message)

                        findNavController().navigate(R.id.action_pregnantWomanServicesFragment_to_pregnantWomanUpdateCounsellingFragment)
                    }

                    is ResponseHandler.OnFailed -> {
                        hideProgressBar()
                        handleError(it.code ?: 0, it.message)
                    }

                    else -> Unit
                }
            }
        }
    }
}
