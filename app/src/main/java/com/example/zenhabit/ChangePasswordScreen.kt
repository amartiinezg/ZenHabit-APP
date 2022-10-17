package com.example.zenhabit

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import com.blogspot.atifsoftwares.animatoolib.Animatoo

class ChangePasswordScreen : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password_screen)

        val btCancel = findViewById<Button>(R.id.btCancel_ChangePasswordScreen)

        btCancel.setOnClickListener{
           var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            Animatoo.animateSlideRight(this)
        }
    }
}