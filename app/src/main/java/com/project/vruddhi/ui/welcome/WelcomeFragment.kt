package com.project.vruddhi.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.project.vruddhi.R
import com.project.vruddhi.base.FragmentBase
import com.project.vruddhi.databinding.FragmentWelcomeBinding

/**
 * Welcome Screen
 */
class WelcomeFragment : FragmentBase() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding
    private var mView: View? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        if (mView == null) {
            _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
            mView = binding?.root
        }
        return mView
    }

    override fun setupToolbar() {}

    override fun initializeScreenVariables() {
        initComponents()
    }

    override fun makeApiCalls() {}

    private fun initComponents() {
        setListeners()
    }

    private fun setListeners() {
        mView?.findViewById<Button?>(R.id.btnLogin)?.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
        }
    }
}