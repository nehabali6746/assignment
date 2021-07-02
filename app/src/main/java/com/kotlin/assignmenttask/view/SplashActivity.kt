package com.kotlin.assignmenttask.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kotlin.assignmenttask.R
import kotlin.concurrent.thread

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        callingSplash()

    }


    private fun callingSplash() {
        thread {
            Thread.sleep(3000)
            val intent = Intent(this, ImageViewActivity::class.java)
            startActivity(intent)

        }
    }
}