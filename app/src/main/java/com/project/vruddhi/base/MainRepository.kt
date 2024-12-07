package com.project.vruddhi.base

import com.project.vruddhi.network.ApiInterface
import com.project.vruddhi.network.ResponseData
import com.project.vruddhi.network.ResponseHandler
import com.project.vruddhi.ui.signup.model.request.SignUpRequestModel
import com.project.vruddhi.ui.signup.model.response.SignUpResponseModel
import kotlinx.coroutines.flow.Flow

/**
 * Main repository class for common API calls
 */
class MainRepository constructor(
    private val apiInterface: ApiInterface
) : BaseRepository()