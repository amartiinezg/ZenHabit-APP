package com.example.zenhabit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.view.Window
import android.widget.Button
import android.content.Intent
import com.blogspot.atifsoftwares.animatoolib.Animatoo

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var buttonRegister_LoginScreen = findViewById<Button>(R.id.btRegister_LoginScreen)
        var btForgotPassword_LoginScreen = findViewById<Button>(R.id.buttonResetPassword)
        var bt_login = findViewById<Button>(R.id.btLogin_LoginScreen)

        buttonRegister_LoginScreen.setOnClickListener{
            val intent = Intent(this, RegistreActivity::class.java)
            startActivity(intent)

            Animatoo.animateSlideLeft(this)
        }

        btForgotPassword_LoginScreen.setOnClickListener{
            val intent = Intent(this, PasswordCodeRequest::class.java)
            startActivity(intent)

            Animatoo.animateSlideLeft(this)
        }

        bt_login.setOnClickListener{
            val intent = Intent(this, Home::class.java)
            startActivity(intent)

            Animatoo.animateSlideLeft(this)
        }

    }


}
