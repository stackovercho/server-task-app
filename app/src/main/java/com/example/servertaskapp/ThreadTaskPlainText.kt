package com.example.servertaskapp

import android.util.Log

class ThreadTaskPlainText(private val activity: MainActivity) : Thread() {
    private var result = "not set yet"
    override fun run() {
        super.run()
        Log.d("ThreadTask", "inside run()")
        result = "hi from thread"
//        activity.updateView(result)

        val updateGui = UpdateGui()
        activity.runOnUiThread(updateGui)
    }

    inner class UpdateGui : Runnable {
        override fun run() {
            activity.updateView(result)
        }
    }
}