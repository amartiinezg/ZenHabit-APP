package com.example.zenhabit.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.zenhabit.R
import com.example.zenhabit.activities.IntroductionActivity_1
import com.example.zenhabit.utilities.DataBaseUtils


class Splash_Screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        //DataBaseUtils.createJardi()
        //DataBaseUtils.createPerfils()
        Handler().postDelayed({
            val intent = Intent(this@Splash_Screen, IntroductionActivity_1::class.java)
            startActivity(intent)
            finish()
        }, 2000)

    }
}