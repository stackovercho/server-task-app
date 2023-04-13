package com.example.servertaskapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val task = ThreadTaskJson(this)
        task.start()
        // do not use the result of the Thread here
    }

    fun updateView(s: String) {
        val tv: TextView = findViewById(R.id.tv)
        tv.text = s
    }

    fun updateViewWithJson(json: String) {

    }

    companion object {
        var URL_PLAIN_TEXT : String = "https://cmsc436-2301.cs.umd.edu/test.txt"
        var URL_BASIC_PHP : String = "https://cmsc436-2301.cs.umd.edu/test.php"
        var URL_GET_PHP : String
                = "https://cmsc436-2301.cs.umd.edu/testGet.php?name=Jill&age=20"
        var URL_POST_PHP : String = "https://cmsc436-2301.cs.umd.edu/testPost.php"
        var URL_JSON : String = "https://cmsc436-2301.cs.umd.edu/json.php"
    }
}