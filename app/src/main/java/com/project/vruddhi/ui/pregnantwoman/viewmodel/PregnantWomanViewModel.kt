package com.project.vruddhi.ui.pregnantwoman.viewmodel

import androidx.lifecycle.viewModelScope
import com.project.vruddhi.base.ViewModelBase
import com.project.vruddhi.network.ResponseDataList
import com.project.vruddhi.network.ResponseHandler
import com.project.vruddhi.ui.pregnantwoman.model.PregnantWomanListResponse
import com.project.vruddhi.ui.pregnantwoman.repository.PregnantWomanRepository
import com.project.vruddhi.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PregnantWomanViewModel @Inject constructor(
    private val pregnantWomanRepository: PregnantWomanRepository
) : ViewModelBase() {
    var pregnantWomanResponse = SingleLiveEvent<ResponseHandler<ResponseDataList<PregnantWomanListResponse>?>>()

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
}