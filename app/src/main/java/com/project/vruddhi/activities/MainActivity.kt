package com.project.vruddhi.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.project.vruddhi.BaseActivity
import com.project.vruddhi.R
import com.project.vruddhi.databinding.ActivityMainBinding
import com.project.vruddhi.ui.welcome.WelcomeFragment


class MainActivity : BaseActivity(), NavController.OnDestinationChangedListener {

    private lateinit var mContext: Context

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    private var mView: View? = null

    private var navHostFrag: NavHostFragment? = null

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        handleGraphNavigation()
        setUpOnBackPressClick()

        setClickListeners()
    }

    private fun handleGraphNavigation() {
        navHostFrag = supportFragmentManager.findFragmentById(R.id.navHostMain) as NavHostFragment
        navController = navHostFrag?.navController
        navController?.addOnDestinationChangedListener(this)
    }

    fun getBindings(): ActivityMainBinding? {
        return binding
    }

    private fun setClickListeners() {
        binding?.layToolbar?.ivBack?.setOnClickListener { navController?.popBackStack() }
    }



    /**
     * Method to show Progress Bar
     */
    fun showProgressBar() {
        binding?.pbProgress?.visibility = View.VISIBLE
    }

    /**
     * Method to hide Progress Bar
     */
    fun hideProgressBar() {
        binding?.pbProgress?.visibility = View.GONE
    }

    /**
     * Method to handle onBack Press event
     */
    private fun setUpOnBackPressClick() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                navHostFrag?.let { navHostFragment ->
                    navHostFragment.childFragmentManager.fragments[0]?.let {
                        when (it) {
                            is WelcomeFragment -> {

                                val intent = Intent(Intent.ACTION_MAIN)
                                intent.addCategory(Intent.CATEGORY_HOME)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                                startActivity(intent)
                                finish()
                            }

                            /*is MyAccountFragment -> {
                                navController?.navigate(R.id.dashboardFragment)
                            }*/

                            else -> {
                                navController?.popBackStack()
                            }
                        }
                    }
                }
            }
        })
    }

    /**
     * Method to restart app after language change
     */
    private fun reStartApp() {
        val intent = this.intent
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        finish()
        startActivity(intent)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {

        when(destination.id) {

            R.id.welcomeFragment -> {
                showToolBar(false)
            }

            else -> {
                showToolBar(true)
            }
        }
    }

    /**
     * Method to show/hide toolbar
     *
     * @param isShow - boolean for show/hide
     */
    private fun showToolBar(isShow : Boolean = true) {
        if (isShow) {
            binding?.layToolbar?.appBarMain?.visibility = View.VISIBLE
        } else {
            binding?.layToolbar?.appBarMain?.visibility = View.GONE
        }
    }


}