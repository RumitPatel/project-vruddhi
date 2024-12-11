package com.project.vruddhi.ui.pregnantwoman.viewmodel

import androidx.lifecycle.viewModelScope
import com.project.vruddhi.base.ViewModelBase
import com.project.vruddhi.network.ResponseData
import com.project.vruddhi.network.ResponseDataList
import com.project.vruddhi.network.ResponseHandler
import com.project.vruddhi.ui.pregnantwoman.model.PregnantWomanCounsellingResponse
import com.project.vruddhi.ui.pregnantwoman.model.PregnantWomanGetScreeningAllResponse
import com.project.vruddhi.ui.pregnantwoman.model.PregnantWomanGetScreeningResponse
import com.project.vruddhi.ui.pregnantwoman.model.PregnantWomanListResponse
import com.project.vruddhi.ui.pregnantwoman.model.PregnantWomanUpdateCounsellingResponse
import com.project.vruddhi.ui.pregnantwoman.model.PregnantWomanUpdateRegistrationResponse
import com.project.vruddhi.ui.pregnantwoman.model.PregnantWomanUpdateScreeningResponse
import com.project.vruddhi.ui.pregnantwoman.model.PregnantWomanUpdateServicesResponse
import com.project.vruddhi.ui.pregnantwoman.model.request.PregnantWomanScreeningUpdateRequest
import com.project.vruddhi.ui.pregnantwoman.model.request.PregnantWomanUpdateCounsellingRequest
import com.project.vruddhi.ui.pregnantwoman.model.request.PregnantWomanUpdateRegistrationRequest
import com.project.vruddhi.ui.pregnantwoman.repository.PregnantWomanRepository
import com.project.vruddhi.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PregnantWomanViewModel @Inject constructor(
    private val pregnantWomanRepository: PregnantWomanRepository
) : ViewModelBase() {

    var mPregnantWomanGetScreeningInfo: PregnantWomanGetScreeningAllResponse? = null

    var pregnantWomanResponse =
        SingleLiveEvent<ResponseHandler<ResponseDataList<PregnantWomanListResponse>?>>()
    var pregnantWomanGetScreeningResponse =
        SingleLiveEvent<ResponseHandler<ResponseData<PregnantWomanGetScreeningResponse>?>>()
    var pregnantWomanGetScreeningAllResponse =
        SingleLiveEvent<ResponseHandler<ResponseDataList<PregnantWomanGetScreeningAllResponse>?>>()
    var pregnantWomanUpdateScreeningResponse =
        SingleLiveEvent<ResponseHandler<ResponseDataList<PregnantWomanUpdateScreeningResponse>?>>()
    var pregnantWomanRegistrationResponse =
        SingleLiveEvent<ResponseHandler<ResponseDataList<PregnantWomanUpdateRegistrationResponse>?>>()
    var pregnantWomanServicesResponse =
        SingleLiveEvent<ResponseHandler<ResponseDataList<PregnantWomanUpdateServicesResponse>?>>()
    var pregnantWomanCounsellingResponse =
        SingleLiveEvent<ResponseHandler<ResponseDataList<PregnantWomanUpdateCounsellingResponse>?>>()

    var pregnantWomanGetCounsellingResponse =
        SingleLiveEvent<ResponseHandler<ResponseDataList<PregnantWomanCounsellingResponse>?>>()

    fun init() {
        pregnantWomanResponse =
            SingleLiveEvent<ResponseHandler<ResponseDataList<PregnantWomanListResponse>?>>()

        pregnantWomanGetScreeningResponse =
            SingleLiveEvent<ResponseHandler<ResponseData<PregnantWomanGetScreeningResponse>?>>()
    }

    /**
     * Method to call Pregnant Woman list api
     */
    fun callPregnantWomanListApi() {
        viewModelScope.launch {
            pregnantWomanRepository.callPregnantWomanListApi().collect {
                pregnantWomanResponse.value = it
            }
        }
    }

    /**
     * Method to call Pregnant Woman get screening
     */
    fun callPregnantWomanGetScreeningApi(userId: Long) {
        viewModelScope.launch {
            pregnantWomanRepository.callPregnantWomanGetScreeningApi(userId).collect {
                pregnantWomanGetScreeningResponse.value = it
            }
        }
    }

    /**
     * Method to call Pregnant Woman get screening for all data
     */
    fun callPregnantWomanGetScreeningAllApi(userId: Long) {
        viewModelScope.launch {
            pregnantWomanRepository.callPregnantWomanGetScreeningAllApi(userId).collect {
                pregnantWomanGetScreeningAllResponse.value = it
            }
        }
    }

    /**
     * Method to call Pregnant Woman update screening api
     */
    fun callPregnantWomanUpdateScreeningApi(
        userId: Long,
        pregnantWomanScreeningUpdateRequest: PregnantWomanScreeningUpdateRequest
    ) {
        viewModelScope.launch {
            pregnantWomanRepository.callPregnantWomanUpdateScreeningApi(
                userId,
                pregnantWomanScreeningUpdateRequest
            ).collect {
                pregnantWomanUpdateScreeningResponse.value = it
            }
        }
    }

    /**
     * Method to call Pregnant Woman update registration api
     */
    fun callPregnantWomanUpdateRegistrationApi(
        screeningId: Long,
        request: PregnantWomanUpdateRegistrationRequest
    ) {
        viewModelScope.launch {
            pregnantWomanRepository.callPregnantWomanUpdateRegistrationApi(screeningId, request)
                .collect {
                    pregnantWomanRegistrationResponse.value = it
                }
        }
    }

    /**
     * Method to call Pregnant Woman update registration api
     */
    fun callPregnantWomanGetCounsellingApi(
        screeningId: Long
    ) {
        viewModelScope.launch {
            pregnantWomanRepository.callPregnantWomanGetCounsellingApi(screeningId)
                .collect {
                    pregnantWomanGetCounsellingResponse.value = it
                }
        }
    }

    /**
     * Method to call Pregnant Woman update services api
     */
    fun callPregnantWomanUpdateServicesApi(
        screeningId: Long,
        request: ArrayList<PregnantWomanUpdateCounsellingRequest>
    ) {
        viewModelScope.launch {
            pregnantWomanRepository.callPregnantWomanUpdateServicesApi(screeningId, request)
                .collect {
                    pregnantWomanServicesResponse.value = it
                }
        }
    }

    /**
     * Method to call Pregnant Woman update counselling api
     */
    fun callPregnantWomanUpdateCounsellingApi(userId: String) {
        viewModelScope.launch {
            pregnantWomanRepository.callPregnantWomanUpdateCounsellingApi(userId).collect {
                pregnantWomanCounsellingResponse.value = it
            }
        }
    }
}