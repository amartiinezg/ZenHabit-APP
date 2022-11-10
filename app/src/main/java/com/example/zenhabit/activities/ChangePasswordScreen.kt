package com.example.zenhabit.activities


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.example.zenhabit.R
import com.example.zenhabit.databinding.ActivityChangePasswordScreenBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ChangePasswordScreen : AppCompatActivity() {
    private lateinit var bin: ActivityChangePasswordScreenBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password_screen)
        auth = Firebase.auth

        bin = ActivityChangePasswordScreenBinding.inflate(layoutInflater)
        setContentView(bin.root)

        val newPassword: String = bin.inputNewPassword.toString()
        val repeatNewPassword: String = bin.inputRepeatNewPassword.toString()
        val btSend: Button = bin.btChangePassword
        val btCancel = findViewById<Button>(R.id.btCancel_ChangePasswordScreen)
        val user = Firebase.auth.currentUser
        val tvsnackbarr: TextView = bin.tvsnackbarr
        val passwordLayout : TextInputLayout = bin.layoutpassword1
        val passwordLayout2 : TextInputLayout = bin.layoutPassword2
        val editTextPassword1 : EditText = bin.inputNewPassword
        val editTextPassword2 : EditText = bin.inputRepeatNewPassword



        editTextPassword1.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                passwordLayout.error = null
            }

            editTextPassword2.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                passwordLayout2.error = null
            }
        }




        btSend.setOnClickListener {
            if (newPassword == repeatNewPassword) {


                user!!.updatePassword(repeatNewPassword)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Snackbar.make(tvsnackbarr, getString(R.string.passwordChangeOk), Snackbar.LENGTH_LONG)
                                .show()

                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)

                            Animatoo.animateSlideRight(this)
                        }
                    }

            } else {
                Snackbar.make(tvsnackbarr, getString(R.string.errorMismatchedPasswords), Snackbar.LENGTH_LONG)
                    .show()

                passwordLayout.error = getString(R.string.errorMismatchedPasswords)
                passwordLayout2.error = getString(R.string.errorMismatchedPasswords)
            }

            utilities(editTextPassword1, editTextPassword2)

            btCancel.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                Animatoo.animateSlideRight(this)
            }
        }
    }


}

    private fun utilities(edittextPassword1: EditText, editTextPassword2: EditText){

        edittextPassword1.clearFocus()
        editTextPassword2.clearFocus()
        editTextPassword2.text?.clear()

    }

    }