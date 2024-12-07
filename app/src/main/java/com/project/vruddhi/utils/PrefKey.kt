package com.project.vruddhi.utils

import com.project.vruddhi.BuildConfig

/**
 * PrefKeys Name
 */
object PrefKey {
    const val PREFERENCE_NAME = BuildConfig.APPLICATION_ID

    const val IS_LOGIN = "isLogin"
    const val TOKEN = "bearerToken"
    const val FIREBASE_TOKEN = "firebaseToken"
    const val IS_UPDATE_TOKEN = "isUpdateToken"
}
