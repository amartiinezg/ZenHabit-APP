package com.example.zenhabit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import com.blogspot.atifsoftwares.animatoolib.Animatoo

class RegistreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registre)

        var btCancel = findViewById<Button>(R.id.btCancel_RegisterScreen)

        btCancel.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            Animatoo.animateSlideRight(this)
        }

    }
}