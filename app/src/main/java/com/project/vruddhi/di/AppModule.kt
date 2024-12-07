package com.project.vruddhi.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.project.vruddhi.utils.PrefKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * DI for app level
 */

//////////////////////////////////////////////////////////////////////////////////////////////////////////


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideSharedPreferencess(@ApplicationContext context: Context): SharedPreferences =
        EncryptedSharedPreferences.create(
            PrefKey.PREFERENCE_NAME,
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
}
