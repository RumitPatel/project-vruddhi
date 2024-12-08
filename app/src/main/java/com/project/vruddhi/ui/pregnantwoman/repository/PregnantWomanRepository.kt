package com.project.vruddhi.ui.pregnantwoman.repository

import com.project.vruddhi.base.BaseRepository
import com.project.vruddhi.network.ApiInterface
import com.project.vruddhi.network.ResponseDataList
import com.project.vruddhi.network.ResponseHandler
import com.project.vruddhi.ui.pregnantwoman.model.PregnantWomanGetScreeningResponse
import com.project.vruddhi.ui.pregnantwoman.model.PregnantWomanListResponse
import com.project.vruddhi.ui.pregnantwoman.model.PregnantWomanUpdateCounsellingResponse
import com.project.vruddhi.ui.pregnantwoman.model.PregnantWomanUpdateRegistrationResponse
import com.project.vruddhi.ui.pregnantwoman.model.PregnantWomanUpdateScreeningResponse
import com.project.vruddhi.ui.pregnantwoman.model.PregnantWomanUpdateServicesResponse
import kotlinx.coroutines.flow.Flow

/**
 * Repository class for Pregnant woman
 */
class PregnantWomanRepository constructor(
    private val apiInterface: ApiInterface
) : BaseRepository() {
    suspend fun callPregnantWomanListApi(): Flow<ResponseHandler<ResponseDataList<PregnantWomanListResponse>?>> {
        return makeAPICallList { apiInterface.getPregnantWomenList() }
    }

    suspend fun callPregnantWomanGetScreeningApi(): Flow<ResponseHandler<ResponseDataList<PregnantWomanGetScreeningResponse>?>> {
        return makeAPICallList { apiInterface.getPregnantWomenGetScreening() }
    }

    suspend fun callPregnantWomanUpdateScreeningApi(
        userId: String,
    ): Flow<ResponseHandler<ResponseDataList<PregnantWomanUpdateScreeningResponse>?>> {
        return makeAPICallList { apiInterface.pregnantWomenUpdateScreening() }
    }

    suspend fun callPregnantWomanUpdateRegistrationApi(
        userId: String,
    ): Flow<ResponseHandler<ResponseDataList<PregnantWomanUpdateRegistrationResponse>?>> {
        return makeAPICallList { apiInterface.pregnantWomenUpdateRegistration() }
    }

    suspend fun callPregnantWomanUpdateServicesApi(
        userId: String,
    ): Flow<ResponseHandler<ResponseDataList<PregnantWomanUpdateServicesResponse>?>> {
        return makeAPICallList { apiInterface.pregnantWomenUpdateServices() }
    }

    suspend fun callPregnantWomanUpdateCounsellingApi(
        userId: String,
    ): Flow<ResponseHandler<ResponseDataList<PregnantWomanUpdateCounsellingResponse>?>> {
        return makeAPICallList { apiInterface.pregnantWomenUpdateCounselling() }
    }
}
