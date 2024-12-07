package com.project.vruddhi.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.project.vruddhi.BuildConfig
import com.project.vruddhi.network.ApiConstants.API_TIME_OUT
import com.project.vruddhi.network.ApiInterface
import com.project.vruddhi.network.HttpHandleIntercept
import com.project.vruddhi.utils.MyPreference
import com.project.vruddhi.utils.PrefKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier

/**
 * DI class for network
 */
@Module
@InstallIn(ViewModelComponent::class)
class NetworkModule {
    /**
     * Generate Retrofit Client
     */
    @Provides
    @RetrofitStore
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val builder = Retrofit.Builder()
        builder.baseUrl(BuildConfig.DEV_BASE_URL)
        builder.addConverterFactory(GsonConverterFactory.create())
        builder.client(okHttpClient)
        return builder.build()
    }

    @Provides
    @ViewModelScoped
    fun provideApiInterface(@RetrofitStore retrofit: Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)

    @Provides
    fun provideSharedPreferencess(@ApplicationContext context: Context): SharedPreferences =
        EncryptedSharedPreferences.create(
            PrefKey.PREFERENCE_NAME,
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    @Provides
    fun provideHttpHandleIntercept(@ApplicationContext context: Context): HttpHandleIntercept =
        HttpHandleIntercept(myPreference = MyPreference(provideSharedPreferencess(context)))


    /**
     * generate OKhttp client
     */
    @Provides
    fun getOkHttpClient(httpHandleIntercept: HttpHandleIntercept): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) logging.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(logging)
        builder
            .readTimeout(API_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(API_TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(API_TIME_OUT, TimeUnit.SECONDS)
            .callTimeout(API_TIME_OUT, TimeUnit.MINUTES)
            .addInterceptor(httpHandleIntercept)
            .build()

        return builder.build()
    }
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RetrofitStore