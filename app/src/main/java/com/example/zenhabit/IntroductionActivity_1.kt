package com.example.zenhabit

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MotionEventCompat

class IntroductionActivity_1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction_1)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return when (MotionEventCompat.getActionMasked(event)) {
            // Display a Toast whenever a movement is captured on the screen
            MotionEvent.ACTION_MOVE -> {
                val swap = Intent(this, Introduction2::class.java)
                startActivity(swap)
                true
            }
            else -> super.onTouchEvent(event)
        }

    }
}