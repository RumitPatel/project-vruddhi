package com.project.vruddhi.ui.signin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.project.vruddhi.R
import com.project.vruddhi.activities.ResetPasswordActivity
import com.project.vruddhi.base.FragmentBase
import com.project.vruddhi.databinding.FragmentLoginBinding
import com.project.vruddhi.extensions.setTitle
import com.project.vruddhi.network.ResponseHandler
import com.project.vruddhi.ui.signin.viewmodel.LoginViewModel

/**
 * Login Fragment
 */
class LoginFragment : FragmentBase() {

    private lateinit var _binding: FragmentLoginBinding
    private val binding get() = _binding
    private var mView: View? = null

    private val viewModel: LoginViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        if (mView == null) {
            _binding = FragmentLoginBinding.inflate(inflater, container, false)
            mView = binding.root
        }
        return mView
    }

    override fun setupToolbar() {
        setTitle(getString(R.string.log_into_account))
    }

    override fun initializeScreenVariables() {

        viewModel.init()

        setListeners()
        setDataObservers()
    }

    override fun makeApiCalls() {

    }

    /**
     * method to set click listener
     */
    private fun setListeners() {

        binding.tvForgotPassword.setOnClickListener {
            startActivity(Intent(requireContext(), ResetPasswordActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {

        }

        mView?.findViewById<Button?>(R.id.btnLogin)?.setOnClickListener {

            val email = binding.etEmail.text.toString()
            val pass = binding.etPassword.text.toString()
            viewModel.callLoginApi(email, pass)
        }
    }

    /**
     * Method to set data observers
     */
    private fun setDataObservers() {

        viewModel.apply {

            loginResponse?.observe(viewLifecycleOwner) {
                when (it) {
                    is ResponseHandler.Loading -> {
                        showProgressBar()
                    }

                    is ResponseHandler.OnSuccessResponse -> {
                        hideProgressBar()

                        it.response?.data?.let {
                            findNavController().navigate(R.id.nav_home)
                            //startActivity(Intent(requireContext(), HomeActivity::class.java))
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