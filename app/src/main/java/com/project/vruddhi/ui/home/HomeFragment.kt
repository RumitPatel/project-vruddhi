package com.project.vruddhi.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import com.project.vruddhi.R
import com.project.vruddhi.base.FragmentBase
import com.project.vruddhi.databinding.FragmentHomeBinding

/**
 * Home Fragment class
 */
class HomeFragment : FragmentBase() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private var mView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        if (mView == null) {
            _binding = FragmentHomeBinding.inflate(inflater, container, false)
            mView = binding?.root
        }
        return mView
    }

    override fun setupToolbar() {

    }

    override fun initializeScreenVariables() {
        setListeners()
    }

    override fun makeApiCalls() {

    }

    /**
     * Method to set click listener
     */
    private fun setListeners() {
        mView?.findViewById<LinearLayout?>(R.id.llPregnantWoman)?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_pregnantWomanListFragment)
            //startActivity(Intent(requireContext(), PregnantWomanListActivity::class.java))
        }
    }

}