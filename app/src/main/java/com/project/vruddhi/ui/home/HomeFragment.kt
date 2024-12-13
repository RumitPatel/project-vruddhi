package com.project.vruddhi.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.project.vruddhi.R
import com.project.vruddhi.activities.MainActivity
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
        binding?.llPregnantWoman?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_pregnantWomanListFragment)
            //startActivity(Intent(requireContext(), PregnantWomanListActivity::class.java))
        }

        binding?.tvDashboard?.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle(getString(R.string.logout))
                .setMessage(getString(R.string.logout_warning))
                .setPositiveButton(android.R.string.yes, { dialog, which ->
                    (activity as MainActivity).logoutApp()
                })

                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
        }
    }
}
