package com.example.zenhabit

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.example.zenhabit.databinding.ActivityMainBinding
import com.example.zenhabit.databinding.ActivityRegistreBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistreActivity : AppCompatActivity() {
    private lateinit var bin: ActivityRegistreBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registre)
        auth = Firebase.auth
        bin =   ActivityRegistreBinding.inflate(layoutInflater)
        setContentView(bin.root)

        var btCancel = bin.btCancelRegisterScreen
        var btRegister = bin.btRegister



        btCancel.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            Animatoo.animateSlideRight(this)
        }

        btRegister.setOnClickListener{
            var email = bin.inputEmailRegisterScreen.text.toString()
            var password = bin.inputPasswordRegisterScreen.text.toString()
            crearUsuari(email,password)
        }

    }

    private fun crearUsuari(email: String, password: String) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")


                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                    Animatoo.animateSlideRight(this)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Error en el registre :" + task.exception, Toast.LENGTH_SHORT).show()

                }
            }
    }
}