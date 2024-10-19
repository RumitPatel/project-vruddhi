package com.project.vruddhi.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

/**
 * Base Viewmodel Class
 */
open class ViewModelBase : ViewModel() {

    var snackbarMessage = MutableLiveData<Any>()
    var progressBarDisplay = MutableLiveData<Boolean>()
    private var hideKeyBoardEvent = MutableLiveData<Any>()

    @Inject
    lateinit var mainRepository:MainRepository

    fun getHideKeyBoardEvent(): MutableLiveData<Any> {
        return hideKeyBoardEvent
    }

    /**
     * This method is used to display the Snake Bar Message
     *
     * @param message string object to display.
     */
    fun showSnackbarMessage(message: Any?) {
        if(message != null && message.toString().isNotEmpty()){
            snackbarMessage.postValue(message.toString())
        }
    }

    fun showProgressBar(display: Boolean) {
        progressBarDisplay.postValue(display)
    }

    fun hideKeyboard() {
        getHideKeyBoardEvent().value = true
    }
}
