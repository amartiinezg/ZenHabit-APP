package com.example.zenhabit.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.example.zenhabit.R

class Introduction2 : AppCompatActivity() {

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction_2)
        val btNext = findViewById<Button>(R.id.bt_Next_Introduction)

            btNext.setOnClickListener{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                Animatoo.animateSlideLeft(this)
            }

    }
}