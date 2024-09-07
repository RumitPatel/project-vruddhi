package com.project.vruddhi.base

import androidx.fragment.app.Fragment
import com.project.vruddhi.R
import com.project.vruddhi.network.ApiConstants
import com.project.vruddhi.network.HttpErrorCode

/**
 * FragmentWrapper class to handle Web service failure case
 *
 */
abstract class FragmentBaseWrapper : Fragment() {
    abstract fun somethingWentWrong(message: String? = getString(R.string.error_something_went_wrong))

    abstract fun noInternet()

    abstract fun dataNotFound(message: String?, messageCode: String?)

    abstract fun unAuthorizationUser(message: String?, messageCode: String?)

    open fun handleError(code: Int = ApiConstants.RESPONSE_CODE_401, message: String? = "") {
        handleException(code, message)
    }

    private fun handleException(code: Int, message: String?) {
        when (code) {
            HttpErrorCode.EMPTY_RESPONSE.code -> dataNotFound(message, code.toString())
            //HttpErrorCode.NEW_VERSION_FOUND.code -> newVersionFound()
            HttpErrorCode.UNAUTHORIZED.code -> unAuthorizationUser(message, code.toString())
            HttpErrorCode.NO_CONNECTION.code -> noInternet()
            else -> somethingWentWrong(message)
        }
    }

}
