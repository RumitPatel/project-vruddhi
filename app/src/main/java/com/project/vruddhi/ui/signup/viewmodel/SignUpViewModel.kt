package com.project.vruddhi.ui.signup.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.vruddhi.base.ViewModelBase
import com.project.vruddhi.network.ResponseData
import com.project.vruddhi.network.ResponseHandler
import com.project.vruddhi.ui.signup.model.request.SignUpRequestModel
import com.project.vruddhi.ui.signup.model.response.SignUpResponseModel
import com.project.vruddhi.ui.signup.repository.SignUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository
) : ViewModelBase() {

    var signUpResponse:MutableLiveData<ResponseHandler<ResponseData<SignUpResponseModel>?>>?=null

    fun init() {
        signUpResponse = MutableLiveData<ResponseHandler<ResponseData<SignUpResponseModel>?>>()
    }
    fun getSignUp(signUpRequest : SignUpRequestModel){
        showProgressBar(true)
        viewModelScope.launch {
             signUpRepository.getSignUp(signUpRequest).collect{
                 signUpResponse?.value = it
             }
        }
    }
}