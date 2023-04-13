package com.example.servertaskapp

import android.util.Log
import java.io.InputStream
import java.net.URL
import java.util.*

class ThreadTaskJson (private val activity: MainActivity) : Thread() {
    private var result = "not set yet"
    override fun run() {
        super.run()
        Log.d("ThreadTask", "inside run()")
        result = "hi from thread"
//        activity.updateView(result)

        try {
            // connect to server, read data, assign it to result
            // create url
            val url = URL(MainActivity.URL_JSON)
            // get input stream from url
            val iStream: InputStream = url.openStream()
            // read from input stream, accumulate into result
            val scan = Scanner(iStream)
            result = ""
            while(scan.hasNext()) {
                result += scan.nextLine()
            }

            val updateGui = UpdateGui()
            activity.runOnUiThread(updateGui)
        } catch (e: Exception) {
            Log.d("ThreadTaskPlainText", "exception: ${e.message}")
        }
    }

    inner class UpdateGui : Runnable {
        override fun run() {
            activity.updateViewWithJson(result)
        }
    }
}