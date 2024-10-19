package com.project.vruddhi

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.project.vruddhi.utils.MyPreference
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var mPref: MyPreference

    //////////////////////////////////////// Overridden Methods /////////////////////////////////////////////

    //////////////////////////////////////// Public Methods ////////////////////////////////////////////////

    /**
     * Method to show snackBar message
     *
     * @param message snackBar Message
     */
    fun showSnackBar(message: String?) {
        Snackbar.make(
            this.findViewById(android.R.id.content)!!,
            message.orEmpty(),
            Snackbar.LENGTH_SHORT
        ).let { snackBar ->
            snackBar.view.let {
                (it.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)!!).let { snackTextView ->
                    snackTextView.maxLines = 5
                    snackTextView.setTextColor(ContextCompat.getColor(this, android.R.color.white))
                }
            }
            snackBar.show()
        }
    }

    /**
     * This method is used to hide the keyboard.
     */
    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    /**
     * This method is used to hide the keyboard.
     */
    fun showKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, 0)
        }
    }
}