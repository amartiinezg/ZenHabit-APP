package com.example.zenhabit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import com.blogspot.atifsoftwares.animatoolib.Animatoo

class PasswordCodeRequest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_code_request)


        var btSendCode = findViewById<Button>(R.id.btSendCode)
        var btCancel = findViewById<Button>(R.id.btCancel_GetCodeScreen)

        btSendCode.setOnClickListener{
            val intent = Intent(this, ChangePasswordScreen::class.java)
            startActivity(intent)

            Animatoo.animateSlideLeft(this)
        }

        btCancel.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            Animatoo.animateSlideRight(this)
        }
    }
}