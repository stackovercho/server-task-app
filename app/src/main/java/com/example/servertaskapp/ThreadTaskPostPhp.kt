package com.example.servertaskapp

import android.util.Log
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.net.URL
import java.net.URLConnection
import java.util.*

class ThreadTaskPostPhp : Thread {
    private lateinit var activity : MainActivity
    private var result : String = "not set yet"

    constructor( activity : MainActivity ) {
        this.activity = activity
    }

    override fun run() {
        super.run()
        Log.w( "MainActivity", "Inside run" )
        // connect to server, read data, assign it to result
        try {
            // create URL
            val url: URL = URL(MainActivity.URL_POST_PHP)
            val connection: URLConnection = url.openConnection()
            connection.doOutput = true

            // step 1: write to URL
            val os: OutputStream = connection.getOutputStream()
            val osw = OutputStreamWriter(os)
            val data: String = "name=Sarah&age=20"
            osw.write(data)
            osw.flush()
            osw.close()

            // step 2: read from URL
            val iStream: InputStream = connection.getInputStream()
            val isr = InputStreamReader(iStream)
            val br = BufferedReader(isr)
            result = ""
            var line: String? = ""
            line = br.readLine()
            while(line != null) {
                result += line
                line = br.readLine()
            }
            isr.close()

            val updateGui: UpdateGui = UpdateGui()
            activity.runOnUiThread(updateGui)
        } catch ( e : Exception ) {
            Log.w( "MainActivity", "Exception: " + e.message )
        }
    }

    inner class UpdateGui : Runnable {
        override fun run() {
            activity.updateView( result )
        }
    }
}