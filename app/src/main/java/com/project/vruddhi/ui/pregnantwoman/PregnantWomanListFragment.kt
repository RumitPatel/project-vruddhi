package com.project.vruddhi.ui.pregnantwoman

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.project.vruddhi.R
import com.project.vruddhi.adapters.PregnantWomanAdapter
import com.project.vruddhi.base.FragmentBase
import com.project.vruddhi.databinding.FragmentPregnantWomanListBinding
import com.project.vruddhi.extensions.setTitle
import com.project.vruddhi.network.ResponseHandler
import com.project.vruddhi.ui.pregnantwoman.model.PregnantWomanListResponse
import com.project.vruddhi.ui.pregnantwoman.viewmodel.PregnantWomanViewModel
import com.project.vruddhi.utils.Constants.PREGNANT_WOMAN_PATIENT_INFO_ID
import com.project.vruddhi.utils.setVerticalLayoutManager

/**
 * Pregnant Woman list class
 *
 */
class PregnantWomanListFragment : FragmentBase() {

    private lateinit var _binding: FragmentPregnantWomanListBinding
    private val binding get() = _binding
    private var mView: View? = null

    private val viewModel: PregnantWomanViewModel by activityViewModels()

    private lateinit var adapterPregnantWoman: PregnantWomanAdapter
    private val mPregnantWomen = ArrayList<PregnantWomanListResponse>()

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
        viewModel.init()
        prepareRecyclerView(mPregnantWomen)
        setRecyclerView()
        setDataObservers()
        setSearch()
    }

    override fun makeApiCalls() {
        viewModel.callPregnantWomanListApi()
    }

    private fun prepareRecyclerView(pregnantWomen: ArrayList<PregnantWomanListResponse>) {
        adapterPregnantWoman = PregnantWomanAdapter(pregnantWomen) {
            val bundle = Bundle()
            if (it.id != null) {
                bundle.putLong(PREGNANT_WOMAN_PATIENT_INFO_ID, it.id)
            }
            findNavController().navigate(
                R.id.action_pregnantWomanListFragment_to_pregnantWomanScreeningFragment,
                bundle
            )
        }
    }

    /**
     * Method to set RecyclerView
     */
    private fun setRecyclerView() {
        setVerticalLayoutManager(activity as AppCompatActivity, binding.rvPregnantWoman)
        binding.rvPregnantWoman.adapter = adapterPregnantWoman
    }

    /**
     * Method to set implementSearch
     */
    private fun setSearch() {
        binding.etPatients.doOnTextChanged { text, _, _, _ ->
            if (text != null && !TextUtils.isEmpty(text)) {
                prepareRecyclerView(getFilteredList(text.toString()))
                setRecyclerView()
            } else {
                prepareRecyclerView(mPregnantWomen)
                setRecyclerView()
            }
        }
    }

    private fun getFilteredList(text: String): ArrayList<PregnantWomanListResponse> {
        val pregnantWomen = ArrayList<PregnantWomanListResponse>()

        for (item in mPregnantWomen) {
            if (item.womenName?.lowercase()?.contains(text.lowercase()) == true) {
                pregnantWomen.add(item)
            }
        }

        return pregnantWomen
    }

    /**
     * Method to set data observers
     */
    private fun setDataObservers() {

        viewModel.apply {

            pregnantWomanResponse.observe(viewLifecycleOwner) {
                when (it) {
                    is ResponseHandler.Loading -> {
                        showProgressBar()
                    }

                    is ResponseHandler.OnSuccessResponse -> {
                        hideProgressBar()

                        it.response?.data?.let {
                            mPregnantWomen.clear()
                            mPregnantWomen.addAll(ArrayList(it))
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
