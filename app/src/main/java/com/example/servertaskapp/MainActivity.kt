package com.example.servertaskapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val task = ThreadTaskBasicPhp(this)
        task.start()
        // do not use the result of the Thread here
    }

    fun updateView(s: String) {
        val tv: TextView = findViewById(R.id.tv)
        tv.text = s
    }

    companion object {
        const val URL_PLAIN_TEXT: String = "https://cmsc436-2301.cs.umd.edu/test.txt"
        const val  URL_BASIC_PHP = "https://cmsc436-2301.cs.umd.edu/test.php"
    }
}