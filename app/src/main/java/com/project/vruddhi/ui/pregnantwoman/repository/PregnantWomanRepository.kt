package com.project.vruddhi.ui.pregnantwoman.repository

import com.project.vruddhi.base.BaseRepository
import com.project.vruddhi.network.ApiInterface
import com.project.vruddhi.network.ResponseDataList
import com.project.vruddhi.network.ResponseHandler
import com.project.vruddhi.ui.pregnantwoman.model.PregnantWomanListResponse
import kotlinx.coroutines.flow.Flow

/**
 * Repository class for login
 */
class PregnantWomanRepository constructor(
    private val apiInterface: ApiInterface
) : BaseRepository() {
    suspend fun callPregnantWomanListApi(): Flow<ResponseHandler<ResponseDataList<PregnantWomanListResponse>?>> {
        return makeAPICallList { apiInterface.getPregnantWomenList() }
    }
}