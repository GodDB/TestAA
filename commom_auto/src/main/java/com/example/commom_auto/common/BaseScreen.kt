package com.example.commom_auto.common

import android.graphics.Rect
import android.util.Log
import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.SurfaceCallback
import androidx.car.app.SurfaceContainer
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

abstract class BaseScreen<SM : BaseScreenModel>(
    carContext: CarContext
) : Screen(carContext), DefaultLifecycleObserver {

    protected abstract val screenModel: SM
    protected val lifecycleScope: CoroutineScope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    private val surfaceCallback: SurfaceCallback = object : SurfaceCallback {
        override fun onSurfaceAvailable(surfaceContainer: SurfaceContainer) {
            this@BaseScreen.onSurfaceAvailable(surfaceContainer)
        }

        override fun onSurfaceDestroyed(surfaceContainer: SurfaceContainer) {
            this@BaseScreen.onSurfaceDestroyed(surfaceContainer)
        }

        override fun onVisibleAreaChanged(visibleArea: Rect) {
            this@BaseScreen.onVisibleAreaChanged(visibleArea)
        }

        override fun onStableAreaChanged(stableArea: Rect) {
            this@BaseScreen.onStableAreaChanged(stableArea)
        }

        override fun onFling(velocityX: Float, velocityY: Float) {
            this@BaseScreen.onFling(velocityX, velocityY)

        }

        override fun onScale(focusX: Float, focusY: Float, scaleFactor: Float) {
            this@BaseScreen.onScale(focusX, focusY, scaleFactor)

        }

        override fun onScroll(distanceX: Float, distanceY: Float) {
            this@BaseScreen.onScroll(distanceX, distanceY)
        }
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        lifecycle.addObserver(this)
        lifecycle.addObserver(screenModel)

        lifecycleScope.launch {
            try {
                CarSurfaceManager.addListener(surfaceCallback)
                awaitCancellation()
            } finally {
                CarSurfaceManager.removeListener(surfaceCallback)
            }
        }

    }

    override fun onDestroy(owner: LifecycleOwner) {
        lifecycleScope.cancel()
        super.onDestroy(owner)
    }

    open fun onSurfaceAvailable(surfaceContainer: SurfaceContainer) {
        Log.e("godgod", "onSurfaceAvailable")
    }

    open fun onSurfaceDestroyed(surfaceContainer: SurfaceContainer) {
        Log.e("godgod", "onSurfaceDestroyed")
    }

    open fun onVisibleAreaChanged(visibleArea: Rect) {
        Log.e("godgod", "onVisibleAreaChanged ${visibleArea}")
    }

    open fun onStableAreaChanged(stableArea: Rect) {
        Log.e("godgod", "onStableAreaChanged ${stableArea}")
    }

    open fun onFling(velocityX: Float, velocityY: Float) {
        Log.e("godgod", "onFling ${velocityX}  ${velocityY}")
    }

    open fun onScale(focusX: Float, focusY: Float, scaleFactor: Float) {
        Log.e("godgod", "onScale ${focusX}  ${focusY} ${scaleFactor}")
    }

    open fun onScroll(distanceX: Float, distanceY: Float) {
        Log.e("godgod", "onScroll ${distanceX}  ${distanceY}")
    }
}
