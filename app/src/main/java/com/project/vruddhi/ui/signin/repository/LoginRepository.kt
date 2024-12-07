package com.project.vruddhi.ui.signin.repository

import com.project.vruddhi.base.BaseRepository
import com.project.vruddhi.network.ApiInterface
import com.project.vruddhi.network.ResponseData
import com.project.vruddhi.network.ResponseHandler
import com.project.vruddhi.ui.signin.model.response.LoginResponseModel
import kotlinx.coroutines.flow.Flow

/**
 * Repository class for login
 */
class LoginRepository constructor(
    private val apiInterface: ApiInterface
) : BaseRepository() {
    suspend fun callLoginApi(
        email: String,
        password: String
    ): Flow<ResponseHandler<ResponseData<LoginResponseModel>?>> {
        return makeAPICall { apiInterface.getLogin(email, password) }
    }
}