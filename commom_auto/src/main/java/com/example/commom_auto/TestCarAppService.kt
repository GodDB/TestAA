package com.example.commom_auto

import android.content.Intent
import android.util.Log
import androidx.car.app.CarAppService
import androidx.car.app.Session
import androidx.car.app.validation.HostValidator

class TestCarAppService : CarAppService() {

    override fun createHostValidator(): HostValidator {
        return HostValidator.ALLOW_ALL_HOSTS_VALIDATOR
    }

    override fun onCreateSession(): Session {
        return TestSession()
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("godgod", "TestCarAppService onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("godgod", "TestCarAppService onDestroy")
    }
}
