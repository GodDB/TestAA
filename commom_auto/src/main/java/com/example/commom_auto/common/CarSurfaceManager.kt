package com.example.commom_auto.common

import android.graphics.Rect
import androidx.car.app.AppManager
import androidx.car.app.CarContext
import androidx.car.app.SurfaceCallback
import androidx.car.app.SurfaceContainer

object CarSurfaceManager {
    private val listeners = mutableListOf<SurfaceCallback>()

    private val surfaceCallback = object : SurfaceCallback {

        override fun onSurfaceAvailable(surfaceContainer: SurfaceContainer) {
            super.onSurfaceAvailable(surfaceContainer)
            listeners.forEach {
                it.onSurfaceAvailable(surfaceContainer)
            }
        }

        override fun onSurfaceDestroyed(surfaceContainer: SurfaceContainer) {
            super.onSurfaceDestroyed(surfaceContainer)
            listeners.forEach {
                it.onSurfaceDestroyed(surfaceContainer)
            }
        }

        override fun onVisibleAreaChanged(visibleArea: Rect) {
            super.onVisibleAreaChanged(visibleArea)
            listeners.forEach {
                it.onVisibleAreaChanged(visibleArea)
            }
        }

        override fun onStableAreaChanged(stableArea: Rect) {
            super.onStableAreaChanged(stableArea)
            listeners.forEach {
                it.onStableAreaChanged(stableArea)
            }
        }

        override fun onFling(velocityX: Float, velocityY: Float) {
            super.onFling(velocityX, velocityY)
            listeners.forEach {
                it.onFling(velocityX, velocityY)
            }
        }

        override fun onScale(focusX: Float, focusY: Float, scaleFactor: Float) {
            super.onScale(focusX, focusY, scaleFactor)
            listeners.forEach {
                it.onScale(focusX, focusY, scaleFactor)
            }
        }

        override fun onScroll(distanceX: Float, distanceY: Float) {
            super.onScroll(distanceX, distanceY)
            listeners.forEach {
                it.onScroll(distanceX, distanceY)
            }
        }

    }

    fun init(carContext: CarContext) {
        carContext.getCarService(AppManager::class.java).setSurfaceCallback(surfaceCallback)
    }

    fun addListener(callback: SurfaceCallback) {
        listeners.add(callback)
    }

    fun removeListener(callback: SurfaceCallback) {
        listeners.remove(callback)
    }
}
