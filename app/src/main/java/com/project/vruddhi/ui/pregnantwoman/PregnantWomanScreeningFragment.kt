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
import com.project.vruddhi.databinding.FragmentPregnanatWomanScreeningBinding
import com.project.vruddhi.extensions.setTitle
import com.project.vruddhi.network.ResponseHandler
import com.project.vruddhi.ui.pregnantwoman.model.request.PregnantWomanScreeningUpdateRequest
import com.project.vruddhi.ui.pregnantwoman.viewmodel.PregnantWomanViewModel
import com.project.vruddhi.utils.Constants.PREGNANT_WOMAN_PATIENT_INFO_ID
import com.project.vruddhi.utils.checkNullAndSet

/**
 * Pregnant Woman screening class
 *
 */
class PregnantWomanScreeningFragment : FragmentBase() {

    private lateinit var _binding: FragmentPregnanatWomanScreeningBinding
    private val binding get() = _binding
    private var mView: View? = null

    private val viewModel: PregnantWomanViewModel by activityViewModels()

    private var mPatientInfoId: Long? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        if (mView == null) {
            _binding =
                FragmentPregnanatWomanScreeningBinding.inflate(inflater, container, false)
            mView = binding.root
        }
        return mView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        getArgumentsData()
        super.onCreate(savedInstanceState)
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
        if (mPatientInfoId != null) {
            viewModel.callPregnantWomanGetScreeningAllApi(mPatientInfoId!!.toLong())
        }
    }

    /**
     * Method to get data from arguments
     */
    private fun getArgumentsData() {
        if (arguments != null && !requireArguments().isEmpty) {
            mPatientInfoId = requireArguments().getLong(PREGNANT_WOMAN_PATIENT_INFO_ID)
        }
    }

    private fun setScreeningProgressBar() {
        binding.includeProgress.tvScreening.setTextColor(
            ContextCompat.getColor(
                requireActivity(),
                R.color.colorPrimary
            )
        )
        binding.includeProgress.ivRoundScreening.setImageResource(R.drawable.round_blue)
    }

    /**
     * Method to set click listener
     */
    private fun setListeners() {
        binding.btnNext.setOnClickListener {

            val request = PregnantWomanScreeningUpdateRequest()
            request.age = binding.tvAge.text.toString().toInt()
            request.dob = binding.tvDob.text.toString()
            request.height = binding.tvHeight.text.toString()
            request.weight = binding.tvWeight.text.toString()
            request.mobile = binding.tvPhoneNo.text.toString()
            request.address = binding.tvVillage.text.toString()
            request.currentMonthOfPregnancy =
                binding.tvCurrentPregnancyMonth.text.toString().toInt()
            request.dateOfLMP = binding.tvLmp.text.toString()
            request.husbandName = binding.tvHusbandName.text.toString()
            request.womenName = binding.tvName.text.toString()

            mPatientInfoId?.let {
                viewModel.callPregnantWomanUpdateScreeningApi(
                    it,
                    request
                )
            }
        }
    }

    /**
     * Method to set data observers
     */
    private fun setDataObservers() {
        viewModel.apply {
            pregnantWomanUpdateScreeningResponse.observe(viewLifecycleOwner) {
                when (it) {
                    is ResponseHandler.Loading -> {
                        showProgressBar()
                    }

                    is ResponseHandler.OnSuccessResponse -> {
                        hideProgressBar()
                        showSnackBar(it.response?.message)
                        findNavController().navigate(R.id.action_pregnantWomanScreeningFragment_to_pregnantWomanRegistrationFragment)
                    }

                    is ResponseHandler.OnFailed -> {
                        hideProgressBar()
                        handleError(it.code ?: 0, it.message)
                    }

                    else -> Unit
                }
            }

            pregnantWomanGetScreeningAllResponse.observe(viewLifecycleOwner) {
                when (it) {
                    is ResponseHandler.Loading -> {
                        showProgressBar()
                    }

                    is ResponseHandler.OnSuccessResponse -> {
                        hideProgressBar()

                        it.response?.data?.let { patientData ->
                            if (patientData.isNotEmpty()) {
                                val data = patientData[0]
                                viewModel.mPregnantWomanGetScreeningInfo = data

                                binding.tvName.checkNullAndSet(data.womenName)
                                binding.tvHusbandName.checkNullAndSet(data.husbandName)
                                binding.tvDob.checkNullAndSet(data.dob)
                                binding.tvAge.checkNullAndSet(data.age.toString())
                                binding.tvVillage.checkNullAndSet(data.village)
                                binding.tvPhoneNo.checkNullAndSet(data.mobile)
                                binding.tvLmp.checkNullAndSet(data.dateOfLMP)
                                binding.tvCurrentPregnancyMonth.checkNullAndSet(data.currentMonthOfPregnancy.toString())
                                binding.tvHeight.checkNullAndSet(data.height.toString())
                                binding.tvWeight.checkNullAndSet(data.weight.toString())
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
        }
    }
}
