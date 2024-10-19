package com.project.vruddhi.ui.signin.viewmodel

import androidx.lifecycle.viewModelScope
import com.project.vruddhi.base.ViewModelBase
import com.project.vruddhi.network.ResponseData
import com.project.vruddhi.network.ResponseHandler
import com.project.vruddhi.ui.signin.model.response.LoginResponseModel
import com.project.vruddhi.ui.signin.repository.LoginRepository
import com.project.vruddhi.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModelBase() {
    var loginResponse: SingleLiveEvent<ResponseHandler<ResponseData<LoginResponseModel>?>>? = null

    fun init() {
        loginResponse = SingleLiveEvent<ResponseHandler<ResponseData<LoginResponseModel>?>>()
    }

    /**
     * Method to call Login Api
     *
     * @param email - Email String
     * @param password - Password String
     */
    fun callLoginApi(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            loginRepository.callLoginApi(email, password).collect {
                loginResponse?.value = it
            }
        }
    }
}