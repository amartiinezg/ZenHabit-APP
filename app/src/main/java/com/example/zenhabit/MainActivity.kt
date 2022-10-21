package com.example.zenhabit

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.view.Window
import android.widget.Button
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.example.zenhabit.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {


    private lateinit var bin: ActivityMainBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bin =   ActivityMainBinding.inflate(layoutInflater)
        setContentView(bin.root)

        var buttonRegister_LoginScreen = findViewById<Button>(R.id.btRegister_LoginScreen)
        var btForgotPassword_LoginScreen = findViewById<Button>(R.id.buttonResetPassword)
        var bt_login = findViewById<Button>(R.id.btLogin_LoginScreen)
        var username = bin.inputUsernameLoginScreen.toString()
        var password = bin.inputPasswordLoginScreen.toString()

        auth = Firebase.auth

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
            IdentificarUsuari(username,password)
        }

    }

    private fun IdentificarUsuari(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")


                    val intent = Intent(this, PasswordCodeRequest::class.java)
                    startActivity(intent)

                    Animatoo.animateSlideLeft(this)


                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()

                }
            }

    }


}
