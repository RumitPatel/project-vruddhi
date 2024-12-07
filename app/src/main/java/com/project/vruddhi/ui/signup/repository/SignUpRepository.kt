package com.project.vruddhi.ui.signup.repository

import com.project.vruddhi.network.*
import com.project.vruddhi.base.BaseRepository
import com.project.vruddhi.ui.signup.model.request.SignUpRequestModel
import com.project.vruddhi.ui.signup.model.response.SignUpResponseModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

/**
 * Repository class for sign up API
 */
@Singleton
class SignUpRepository constructor(
    private val apiInterface: ApiInterface
) : BaseRepository() {
    suspend fun getSignUp(
        model: SignUpRequestModel
    ): Flow<ResponseHandler<ResponseData<SignUpResponseModel>?>> {
        return makeAPICall { apiInterface.getSignUp(model) }
    }
}