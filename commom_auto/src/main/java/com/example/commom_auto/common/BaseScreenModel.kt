package com.example.commom_auto.common

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

abstract class BaseScreenModel : DefaultLifecycleObserver {

    val viewModelScope: CoroutineScope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    override fun onDestroy(owner: LifecycleOwner) {
        onCleared()
        viewModelScope.cancel()
        super.onDestroy(owner)
    }

    open fun onCleared() {

    }
}
