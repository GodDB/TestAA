package com.example.commom_auto.common

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

abstract class BaseScreen<SM : BaseScreenModel>(
    carContext: CarContext
) : Screen(carContext), DefaultLifecycleObserver {

    protected abstract val screenModel: SM
    protected val lifecycleScope: CoroutineScope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        lifecycle.addObserver(this)
        lifecycle.addObserver(screenModel)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        lifecycleScope.cancel()
        super.onDestroy(owner)
    }
}
