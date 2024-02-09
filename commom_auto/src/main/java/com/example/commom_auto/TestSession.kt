package com.example.commom_auto

import android.content.Intent
import android.util.Log
import androidx.car.app.Screen
import androidx.car.app.Session
import com.example.commom_auto.common.CarSurfaceManager
import com.example.commom_auto.home.HomeScreen

class TestSession : Session() {
    override fun onCreateScreen(intent: Intent): Screen {
        Log.e("godgod", "TestSession onCreateScreen")
        CarSurfaceManager.init(carContext)
        return HomeScreen(carContext)
    }
}
