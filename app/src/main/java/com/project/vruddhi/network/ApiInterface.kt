package com.project.vruddhi.network

import com.project.vruddhi.BuildConfig.DEV_BASE_URL
import com.project.vruddhi.BuildConfig.LOGIN
import com.project.vruddhi.BuildConfig.SIGN_UP
import com.project.vruddhi.ui.signin.model.request.LoginRequestModel
import com.project.vruddhi.ui.signin.model.response.LoginResponseModel
import com.project.vruddhi.ui.signup.model.request.SignUpRequestModel
import com.project.vruddhi.ui.signup.model.response.SignUpResponseModel
import retrofit2.Response
import retrofit2.http.*
import javax.inject.Singleton

/**
 * Interface used for api
 */
@Singleton
interface ApiInterface {


    /** Login Signup APIs **/

    @GET(DEV_BASE_URL + SIGN_UP)
    suspend fun getAvatarList(
        @Query("user_type") userType: String
    ): Response<ResponseData<SignUpResponseModel>>

    @POST(DEV_BASE_URL + SIGN_UP)
    suspend fun getSignUp(
        @Body request: SignUpRequestModel
    ): Response<ResponseData<SignUpResponseModel>>

    @POST(DEV_BASE_URL + LOGIN)
    suspend fun getLogin(
        @Body request: LoginRequestModel
    ): Response<ResponseData<LoginResponseModel>>

    /*@FormUrlEncoded
    @POST(DEV_BASE_URL)
    suspend fun getLogin(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("device_type") deviceType: String,
        @Field("device_token") firebaseToken: String
    ): Response<ResponseData<SignUpResponseModel>>

    @GET(DEV_BASE_URL)
    suspend fun getCountry(): Response<ResponseDataList<SignUpResponseModel>>

    @FormUrlEncoded
    @POST(DEV_BASE_URL)
    suspend fun getState(
        @Field("country_id") countryId: String
    ): Response<ResponseDataList<SignUpResponseModel>>

    @GET(DEV_BASE_URL)
    suspend fun getCms(): Response<ResponseData<SignUpResponseModel>>

    @FormUrlEncoded
    @POST(DEV_BASE_URL)
    suspend fun getUniqueEmail(
        @Field("email") email: String
    ): Response<ResponseData<Boolean>>

    @FormUrlEncoded
    @POST(DEV_BASE_URL)
    suspend fun getUniquePhone(
        @Field("phone_number") phone: String
    ): Response<ResponseData<Boolean>>

    @FormUrlEncoded
    @POST(DEV_BASE_URL)
    suspend fun getVerifyOtp(
        @Field("email") email: String,
        @Field("otp") otp: String,
    ): Response<ResponseData<SignUpResponseModel>>

    @FormUrlEncoded
    @POST(DEV_BASE_URL)
    suspend fun getResendOtp(
        @Field("email") email: String
    ): Response<ResponseMetaData>

    @FormUrlEncoded
    @POST(DEV_BASE_URL)
    suspend fun getForgotPassword(
        @Field("email") email: String,
        @Field("platform") platform: String,
        @Field("role") role: String
    ): Response<ResponseMetaData>*/
}
