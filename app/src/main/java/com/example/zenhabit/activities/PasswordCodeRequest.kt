package com.example.zenhabit.activities

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.example.zenhabit.R
import com.example.zenhabit.databinding.ActivityPasswordCodeRequestBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class PasswordCodeRequest : AppCompatActivity() {
    private lateinit var bin: ActivityPasswordCodeRequestBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_code_request)
        auth = Firebase.auth

        bin = ActivityPasswordCodeRequestBinding.inflate(layoutInflater)
        setContentView(bin.root)

        val btSend = findViewById<Button>(R.id.btSendCode)
        val btCancel = findViewById<Button>(R.id.btCancel_GetCodeScreen)
        val tvsnackbar: TextView = bin.tvsnackbar

        btSend.setOnClickListener {
            val email: String = bin.inputEmailGetCodeScreen.text.toString().trim()
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)

                        Animatoo.animateSlideRight(this)
                    }else{
                        Log.w(ContentValues.TAG, "requestCodeFailed", task.exception)
                    }
                }

        }






        btCancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            Animatoo.animateSlideRight(this)
        }

    }
}
