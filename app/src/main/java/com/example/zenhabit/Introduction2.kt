package com.example.zenhabit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.blogspot.atifsoftwares.animatoolib.Animatoo

class Introduction2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction_2)
        val butonNext_Introduction2 = findViewById<Button>(R.id.bt_Next_Introduction)

            butonNext_Introduction2.setOnClickListener{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                Animatoo.animateSlideLeft(this)
            }

    }
}