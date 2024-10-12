package com.project.vruddhi.ui.pregnantwoman

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.project.vruddhi.R
import com.project.vruddhi.activities.PregnantWomanScreeningActivity
import com.project.vruddhi.adapters.PregnantWomanAdapter
import com.project.vruddhi.base.FragmentBase
import com.project.vruddhi.databinding.FragmentPregnantWomanListBinding
import com.project.vruddhi.extensions.setTitle
import com.project.vruddhi.network.ResponseHandler
import com.project.vruddhi.ui.pregnantwoman.model.PregnantWomanListResponse
import com.project.vruddhi.ui.pregnantwoman.viewmodel.PregnantWomanViewModel
import com.project.vruddhi.utils.setVerticalLayoutManager

/**
 * Pregnant Woman list class
 *
 */
class PregnantWomanListFragment : FragmentBase() {

    private lateinit var _binding: FragmentPregnantWomanListBinding
    private val binding get() = _binding
    private var mView: View? = null

    private val viewModel: PregnantWomanViewModel by viewModels()

    private lateinit var adapterPregnantWoman: PregnantWomanAdapter
    private val listPregnantWoman = ArrayList<PregnantWomanListResponse>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        if (mView == null) {
            _binding = FragmentPregnantWomanListBinding.inflate(inflater, container, false)
            mView = binding.root
        }
        return mView
    }

    override fun setupToolbar() {
        setTitle(getString(R.string.pregnant_woman))
    }

    override fun initializeScreenVariables() {

        adapterPregnantWoman = PregnantWomanAdapter(listPregnantWoman) {
            val patientInfo = it
            startActivity(Intent(requireContext(), PregnantWomanScreeningActivity::class.java))
        }

        viewModel.init()
        setRecyclerView()
        setDataObservers()
    }

    override fun makeApiCalls() {
        viewModel.callPregnantWomanListApi()
    }

    /**
     * Method to set RecyclerView
     */
    private fun setRecyclerView() {
        setVerticalLayoutManager(activity as AppCompatActivity, binding.rvPregnantWoman)
        binding.rvPregnantWoman.adapter = adapterPregnantWoman
    }

    /**
     * Method to set data observers
     */
    private fun setDataObservers() {

        viewModel.apply {

            pregnantWomanResponse?.observe(viewLifecycleOwner) {
                when (it) {
                    is ResponseHandler.Loading -> {
                        showProgressBar()
                    }

                    is ResponseHandler.OnSuccessResponse -> {
                        hideProgressBar()

                        it.response?.data?.let {
                            listPregnantWoman.clear()
                            listPregnantWoman.addAll(ArrayList(it))
                            adapterPregnantWoman.notifyDataSetChanged()
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