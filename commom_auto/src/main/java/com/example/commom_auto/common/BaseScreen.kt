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
        Log.e("godgod", "$this onCreate")
        lifecycleScope.launch {
            try {
                CarSurfaceManager.addListener(surfaceCallback)
                awaitCancellation()
            } finally {
                CarSurfaceManager.removeListener(surfaceCallback)
            }
        }
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Log.e("godgod", "$this onStart")
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Log.e("godgod", "$this onResume")
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        Log.e("godgod", "$this onPause")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Log.e("godgod", "$this onStop")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        lifecycleScope.cancel()
        super.onDestroy(owner)
        Log.e("godgod", "$this onDestroy")
    }

    open fun onSurfaceAvailable(surfaceContainer: SurfaceContainer) {
        Log.e("godgod", "$this onSurfaceAvailable")
    }

    open fun onSurfaceDestroyed(surfaceContainer: SurfaceContainer) {
        Log.e("godgod", "$this onSurfaceDestroyed")
    }

    open fun onVisibleAreaChanged(visibleArea: Rect) {
        Log.e("godgod", "$this onVisibleAreaChanged ${visibleArea}")
    }

    open fun onStableAreaChanged(stableArea: Rect) {
        Log.e("godgod", "$this onStableAreaChanged ${stableArea}")
    }

    open fun onFling(velocityX: Float, velocityY: Float) {
        Log.e("godgod", "$this onFling ${velocityX}  ${velocityY}")
    }

    open fun onScale(focusX: Float, focusY: Float, scaleFactor: Float) {
        Log.e("godgod", "$this onScale ${focusX}  ${focusY} ${scaleFactor}")
    }

    open fun onScroll(distanceX: Float, distanceY: Float) {
        Log.e("godgod", "$this onScroll ${distanceX}  ${distanceY}")
    }
}
