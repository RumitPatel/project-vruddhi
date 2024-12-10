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
import com.project.vruddhi.databinding.FragmentPregnanatWomanRegistrationBinding
import com.project.vruddhi.extensions.setTitle
import com.project.vruddhi.network.ResponseHandler
import com.project.vruddhi.ui.pregnantwoman.model.request.PregnantWomanUpdateRegistrationRequest
import com.project.vruddhi.ui.pregnantwoman.viewmodel.PregnantWomanViewModel

/**
 * Pregnant Woman screening class
 *
 */
class PregnantWomanRegistrationFragment : FragmentBase() {

    private lateinit var _binding: FragmentPregnanatWomanRegistrationBinding
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
                FragmentPregnanatWomanRegistrationBinding.inflate(inflater, container, false)
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
                R.color.colorPrimary
            )
        )
        binding.includeProgress.ivRoundRegistration.setImageResource(R.drawable.round_blue)
    }

    /**
     * Method to set click listener
     */
    private fun setListeners() {
        binding.btnNext.setOnClickListener {
            val request = PregnantWomanUpdateRegistrationRequest()
            request.userId = viewModel.mPregnantWomanGetScreeningInfo?.id
            viewModel.mPregnantWomanGetScreeningInfo?.id?.toLong()
                ?.let { it1 ->
                    viewModel.callPregnantWomanUpdateRegistrationApi(
                        userId = it1,
                        request = request
                    )
                }
        }
    }

    /**
     * Method to set data observers
     */
    private fun setDataObservers() {
        viewModel.apply {
            pregnantWomanRegistrationResponse?.observe(viewLifecycleOwner) {
                when (it) {
                    is ResponseHandler.Loading -> {
                        showProgressBar()
                    }

                    is ResponseHandler.OnSuccessResponse -> {
                        hideProgressBar()

                        it.response?.data?.let {

                        }

                        findNavController().navigate(R.id.action_pregnantWomanRegistrationFragment_to_pregnantWomanServicesFragment)
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
