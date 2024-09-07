package com.project.vruddhi.di

import com.project.vruddhi.base.MainRepository
import com.project.vruddhi.network.ApiInterface
import com.project.vruddhi.ui.signin.repository.LoginRepository
import com.project.vruddhi.ui.signup.repository.SignUpRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Repository module class for DI
 */
@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideLoginRepository(apiInterface: ApiInterface) = LoginRepository(apiInterface)
    @Provides
    @ViewModelScoped
    fun provideSignUpRepository(apiInterface: ApiInterface) = SignUpRepository(apiInterface)
    @Provides
    @ViewModelScoped
    fun mainRepository(apiInterface: ApiInterface) = MainRepository(apiInterface)
}