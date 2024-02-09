package com.example.commom_auto.home

import androidx.car.app.CarContext
import androidx.car.app.model.Action
import androidx.car.app.model.ActionStrip
import androidx.car.app.model.MessageTemplate
import androidx.car.app.model.Template
import androidx.car.app.navigation.model.NavigationTemplate
import androidx.lifecycle.LifecycleOwner
import com.example.commom_auto.common.BaseScreen

internal class HomeScreen(carContext: CarContext) : BaseScreen<HomeScreenModel>(carContext) {

    override val screenModel: HomeScreenModel = HomeScreenModel()

    init {
        lifecycle.addObserver(this)
        lifecycle.addObserver(screenModel)
    }

    override fun onGetTemplate(): Template {
        return NavigationTemplate.Builder()
            .setActionStrip(
                ActionStrip.Builder()
                    .addAction(Action.BACK)
                    .build()
            )
            .build()
    }

}
