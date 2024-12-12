package com.project.vruddhi.ui.pregnantwoman.repository

import com.project.vruddhi.base.BaseRepository
import com.project.vruddhi.network.ApiInterface
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

    suspend fun callPregnantWomanGetScreeningApi(userId: Long): Flow<ResponseHandler<ResponseData<PregnantWomanGetScreeningResponse>?>> {
        return makeAPICall { apiInterface.getPregnantWomenGetScreening(id = userId) }
    }

    suspend fun callPregnantWomanGetScreeningAllApi(userId: Long): Flow<ResponseHandler<ResponseDataList<PregnantWomanGetScreeningAllResponse>?>> {
        return makeAPICallList { apiInterface.getPregnantWomenGetScreeningAll(id = userId) }
    }

    suspend fun callPregnantWomanUpdateScreeningApi(
        userId: Long,
        request: PregnantWomanScreeningUpdateRequest
    ): Flow<ResponseHandler<ResponseDataList<PregnantWomanUpdateScreeningResponse>?>> {
        return makeAPICallList {
            apiInterface.pregnantWomenUpdateScreening(
                id = userId,
                pregnantWomanScreeningUpdateRequest = request
            )
        }
    }

    suspend fun callPregnantWomanUpdateRegistrationApi(
        screeningId: Long,
        request: PregnantWomanUpdateRegistrationRequest
    ): Flow<ResponseHandler<ResponseDataList<PregnantWomanUpdateRegistrationResponse>?>> {
        return makeAPICallList {
            apiInterface.pregnantWomenUpdateRegistration(
                id = screeningId,
                pregnantWomanUpdateRegistrationRequest = request
            )
        }
    }

    suspend fun callPregnantWomanGetCounsellingApi(
        screeningId: Long
    ): Flow<ResponseHandler<ResponseDataList<PregnantWomanCounsellingResponse>?>> {
        return makeAPICallList {
            apiInterface.getPregnantWomenGetCounselling(
                id = screeningId
            )
        }
    }

    suspend fun callPregnantWomanUpdateServicesApi(
        screeningId: Long,
        request: ArrayList<PregnantWomanUpdateCounsellingRequest>
    ): Flow<ResponseHandler<ResponseDataList<PregnantWomanUpdateServicesResponse>?>> {
        return makeAPICallList {
            apiInterface.pregnantWomenUpdateServices(
                id = screeningId,
                request = request
            )
        }
    }

    suspend fun callPregnantWomanUpdateCounsellingApi(
        userId: String,
    ): Flow<ResponseHandler<ResponseDataList<PregnantWomanUpdateCounsellingResponse>?>> {
        return makeAPICallList { apiInterface.pregnantWomenUpdateCounselling() }
    }
}
