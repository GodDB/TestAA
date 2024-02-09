package com.example.commom_auto

import android.content.Intent
import androidx.car.app.Screen
import androidx.car.app.Session
import com.example.commom_auto.common.CarSurfaceManager
import com.example.commom_auto.home.HomeScreen

class TestSession : Session() {
    override fun onCreateScreen(intent: Intent): Screen {
        CarSurfaceManager.init(carContext)
        return HomeScreen(carContext)
    }
}
