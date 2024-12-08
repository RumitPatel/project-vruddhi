package com.project.vruddhi.ui.pregnantwoman

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.project.vruddhi.R
import com.project.vruddhi.base.FragmentBase
import com.project.vruddhi.databinding.FragmentPregnanatWomanScreeningBinding
import com.project.vruddhi.extensions.setTitle
import com.project.vruddhi.network.ResponseHandler
import com.project.vruddhi.ui.pregnantwoman.model.PregnantWomanListResponse
import com.project.vruddhi.ui.pregnantwoman.viewmodel.PregnantWomanViewModel

/**
 * Pregnant Woman screening class
 *
 */
class PregnantWomanScreeningFragment : FragmentBase() {

    private lateinit var _binding: FragmentPregnanatWomanScreeningBinding
    private val binding get() = _binding
    private var mView: View? = null

    private val viewModel: PregnantWomanViewModel by viewModels()

    private val listPregnantWoman = ArrayList<PregnantWomanListResponse>()
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
        viewModel.callPregnantWomanGetScreeningApi()
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
        _binding.btnNext.setOnClickListener {
            viewModel.callPregnantWomanUpdateScreeningApi("14")
        }
    }

    /**
     * Method to set data observers
     */
    private fun setDataObservers() {
        viewModel.apply {
            pregnantWomanScreeningResponse?.observe(viewLifecycleOwner) {
                when (it) {
                    is ResponseHandler.Loading -> {
                        showProgressBar()
                    }

                    is ResponseHandler.OnSuccessResponse -> {
                        hideProgressBar()

                        it.response?.data?.let {

                        }

                        findNavController().navigate(R.id.action_pregnantWomanScreeningFragment_to_pregnantWomanRegistrationFragment)
                    }

                    is ResponseHandler.OnFailed -> {
                        hideProgressBar()
                        handleError(it.code ?: 0, it.message)
                    }

                    else -> Unit
                }
            }

            pregnantWomanGetScreeningResponse?.observe(viewLifecycleOwner) {
                when (it) {
                    is ResponseHandler.Loading -> {
                        showProgressBar()
                    }

                    is ResponseHandler.OnSuccessResponse -> {
                        hideProgressBar()

                        it.response?.data?.let {
                            val aaa = it
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
