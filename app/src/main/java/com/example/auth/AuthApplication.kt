package com.example.auth

import android.app.Application
import com.example.auth.di.appModules
import com.example.auth.di.countryPickerModule
import com.example.auth.di.loginModule
import com.example.auth.di.signUpModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class AuthApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AuthApplication)
            modules(appModules)
        }
    }
}