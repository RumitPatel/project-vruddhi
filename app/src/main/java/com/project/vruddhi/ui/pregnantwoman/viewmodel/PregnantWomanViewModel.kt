package com.project.vruddhi.ui.pregnantwoman.viewmodel

import androidx.lifecycle.viewModelScope
import com.project.vruddhi.base.ViewModelBase
import com.project.vruddhi.network.ResponseDataList
import com.project.vruddhi.network.ResponseHandler
import com.project.vruddhi.ui.pregnantwoman.model.PregnantWomanListResponse
import com.project.vruddhi.ui.pregnantwoman.model.PregnantWomanUpdateCounsellingResponse
import com.project.vruddhi.ui.pregnantwoman.model.PregnantWomanUpdateRegistrationResponse
import com.project.vruddhi.ui.pregnantwoman.model.PregnantWomanUpdateScreeningResponse
import com.project.vruddhi.ui.pregnantwoman.model.PregnantWomanUpdateServicesResponse
import com.project.vruddhi.ui.pregnantwoman.repository.PregnantWomanRepository
import com.project.vruddhi.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PregnantWomanViewModel @Inject constructor(
    private val pregnantWomanRepository: PregnantWomanRepository
) : ViewModelBase() {
    var pregnantWomanResponse =
        SingleLiveEvent<ResponseHandler<ResponseDataList<PregnantWomanListResponse>?>>()
    var pregnantWomanScreeningResponse =
        SingleLiveEvent<ResponseHandler<ResponseDataList<PregnantWomanUpdateScreeningResponse>?>>()
    var pregnantWomanRegistrationResponse =
        SingleLiveEvent<ResponseHandler<ResponseDataList<PregnantWomanUpdateRegistrationResponse>?>>()
    var pregnantWomanServicesResponse =
        SingleLiveEvent<ResponseHandler<ResponseDataList<PregnantWomanUpdateServicesResponse>?>>()
    var pregnantWomanCounsellingResponse =
        SingleLiveEvent<ResponseHandler<ResponseDataList<PregnantWomanUpdateCounsellingResponse>?>>()

    fun init() {
        pregnantWomanResponse =
            SingleLiveEvent<ResponseHandler<ResponseDataList<PregnantWomanListResponse>?>>()
    }

    /**
     * Method to call Pregnant Woman list api
     */
    fun callPregnantWomanListApi() {
        viewModelScope.launch {
            pregnantWomanRepository.callPregnantWomanListApi().collect {
                pregnantWomanResponse?.value = it
            }
        }
    }

    /**
     * Method to call Pregnant Woman update screening api
     */
    fun callPregnantWomanUpdateScreeningApi(userId: String) {
        viewModelScope.launch {
            pregnantWomanRepository.callPregnantWomanUpdateScreeningApi(userId).collect {
                pregnantWomanScreeningResponse?.value = it
            }
        }
    }

    /**
     * Method to call Pregnant Woman update registration api
     */
    fun callPregnantWomanUpdateRegistrationApi(userId: String) {
        viewModelScope.launch {
            pregnantWomanRepository.callPregnantWomanUpdateRegistrationApi(userId).collect {
                pregnantWomanRegistrationResponse?.value = it
            }
        }
    }

    /**
     * Method to call Pregnant Woman update services api
     */
    fun callPregnantWomanUpdateServicesApi(userId: String) {
        viewModelScope.launch {
            pregnantWomanRepository.callPregnantWomanUpdateServicesApi(userId).collect {
                pregnantWomanServicesResponse?.value = it
            }
        }
    }

    /**
     * Method to call Pregnant Woman update counselling api
     */
    fun callPregnantWomanUpdateCounsellingApi(userId: String) {
        viewModelScope.launch {
            pregnantWomanRepository.callPregnantWomanUpdateCounsellingApi(userId).collect {
                pregnantWomanCounsellingResponse?.value = it
            }
        }
    }
}