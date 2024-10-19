package com.project.vruddhi.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.project.vruddhi.R
import com.project.vruddhi.activities.MainActivity
import com.project.vruddhi.network.HttpErrorCode
import com.project.vruddhi.utils.MyPreference
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/**
 * Abstract base class for Fragments
 *
 * @author Kirit Khant
 * @since 27-11-2023
 */

@AndroidEntryPoint
abstract class FragmentBase : FragmentBaseWrapper() {

    private val mainActivity by lazy {
        when (activity) {
            is MainActivity -> {
                activity as MainActivity?
            }

            else -> {
                activity as MainActivity?
            }
        }
    }

    @Inject
    lateinit var mPref: MyPreference

    /**
     * This is the generic method where we have to setup the toolbar from single place and
     * from all other extended fragment should have to manage the logic related to the toolbar
     * from this method
     */
    abstract fun setupToolbar()

    /**
     * This is the method from where we are initialized the
     * fragment specific data members and methods.
     */
    abstract fun initializeScreenVariables()

    /**
     * This method is to make api calls
     */
    abstract fun makeApiCalls()

    private fun handleException(code: Int, message: String, messageCode: String) {
        when (code) {
            HttpErrorCode.EMPTY_RESPONSE.code -> dataNotFound(message, messageCode)
            //HttpErrorCode.NEW_VERSION_FOUND.code -> newVersionFound()
            HttpErrorCode.UNAUTHORIZED.code -> unAuthorizationUser(message, messageCode)
            HttpErrorCode.NO_CONNECTION.code -> noInternet()
            else -> somethingWentWrong(message)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        initializeScreenVariables()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        makeApiCalls()
    }

    /**
     * This is generic method based on the MutableLive Data Concept where value changed with toolbar setup
     * will reflect and decide if value is true then it will display.
     */
    /*private fun observeToolbar() {
        viewModel.toolBarModel.observe(viewLifecycleOwner) {
            if (requireActivity() is MainActivity) {
                (requireActivity() as MainActivity).toolBarManagement(it)
            }
        }
    }*/
    fun launchWithFirstScreen(intent: Intent) {
        intent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        activity?.finish()
    }

    /**
     * This is generic method based on the MutableLive Data Concept where value changed with Snake bar
     * will reflect and display the message with Snake bar.
     */
    fun showSnackBar(message: String?) {
        mainActivity?.showSnackBar(message)
    }

    /**
     * This method is to show progress bar
     */
    fun showProgressBar() {
        mainActivity?.showProgressBar()
    }

    /**
     * This method is to hide progress bar
     */
    fun hideProgressBar() {
        mainActivity?.hideProgressBar()
    }

    /**
     * This method is to hide keyboard
     */
    fun hideKeyboard() {
        mainActivity?.hideKeyboard()
    }

    /**
     * This method is to show keyboard
     */
    fun showKeyboard() {
        mainActivity?.showKeyboard()
    }

    /**
     * This is the generic method from where the message will display if something went wrong.
     * This will called based from the make Api called and decide the response with response code.
     */
    override fun somethingWentWrong(message: String?) {
        message?.let {
            mainActivity?.showSnackBar(message)
        }
    }


    /**
     * This is the generic method from where the message will display if Internet connection not available.
     * This will called based from the make Api called and decide the response with response code.
     */
    override fun noInternet() {
        mainActivity?.showSnackBar(getString(R.string.no_internet))
    }


    /**
     * This is the generic method from where the message will display if No Data Found.
     * This will called based from the make Api called and decide the response with response code.
     */
    override fun dataNotFound(message: String?, messageCode: String?) {
        message?.let { mainActivity?.showSnackBar(it) }
    }


    /**
     * This is the generic method from where the message will display if user is unauthorized.
     * This will called based from the make Api called and decide the response with response code.
     */
    override fun unAuthorizationUser(message: String?, messageCode: String?) {
        var msg = message
        var msgCode = messageCode
        if (msg.isNullOrBlank()) {
            msg = getString(R.string.txt_logout_401)
        }

        if (messageCode.isNullOrBlank()) {
            msgCode = getString(R.string.txt_logout_401)
        }

        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }
}