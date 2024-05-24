package com.handy.projetfinalenativelependu

import android.os.Handler
import android.os.Looper
import android.widget.TextView
import java.util.concurrent.TimeUnit

class GameTimer(private val updateInterval: Long = 1000L) {
    private var startTime: Long = 0L
    private var elapsedTime: Long = 0L
    private var running: Boolean = false

    private val handler = Handler(Looper.getMainLooper())
    private val updateTask = object : Runnable {
        override fun run() {
            if (running) {
                elapsedTime = System.currentTimeMillis() - startTime
                handler.postDelayed(this, updateInterval)
            }
        }
    }

    fun startTimer() {
        if (!running) {
            startTime = System.currentTimeMillis() - elapsedTime
            running = true
            handler.post(updateTask)
        }
    }

    fun stopTimer() {
        if (running) {
            running = false
            handler.removeCallbacks(updateTask)
        }
    }

    fun getFormattedTime(): String {
        val hours = TimeUnit.MILLISECONDS.toHours(elapsedTime)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(elapsedTime) % 60
        val seconds = TimeUnit.MILLISECONDS.toSeconds(elapsedTime) % 60
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}