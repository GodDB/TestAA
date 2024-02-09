package com.example.commom_auto.home

import androidx.car.app.CarContext
import androidx.car.app.model.Action
import androidx.car.app.model.MessageTemplate
import androidx.car.app.model.Template
import androidx.lifecycle.LifecycleOwner
import com.example.commom_auto.common.BaseScreen

internal class HomeScreen(carContext: CarContext) : BaseScreen<HomeScreenModel>(carContext) {

    override val screenModel: HomeScreenModel = HomeScreenModel()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)



    }

    override fun onGetTemplate(): Template {
        return MessageTemplate.Builder("Place not found")
            .setHeaderAction(Action.BACK)
            .build()
    }

}
