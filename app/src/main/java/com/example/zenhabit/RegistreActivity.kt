package com.example.zenhabit

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.example.zenhabit.databinding.ActivityRegistreBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class RegistreActivity : AppCompatActivity() {
    private lateinit var bin: ActivityRegistreBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registre)
        auth = Firebase.auth
        bin =   ActivityRegistreBinding.inflate(layoutInflater)
        setContentView(bin.root)

        val btCancel = bin.btCancelRegisterScreen
        val btRegister = bin.btRegister
        var editTextUsername = bin.inputUsernameRegisterScreen
        var editTextPassword = bin.inputPasswordRegisterScreen
        var editTextEmail = bin.inputEmailRegisterScreen
        var layoutUsername = bin.usernameLayoutRegister
        var layoutPassword = bin.passwordLayoutRegister
        var layoutEmail = bin.emailLayout
        var tvsnackbar = bin.snackbarRegister

        //Focused Listeners

        editTextUsername.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                layoutUsername.error = null
            } else {

            }
        }

        editTextPassword.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                layoutPassword.error = null
            } else {

            }
        }

        editTextEmail.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                layoutEmail.error = null
            } else {

            }
        }


        btCancel.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            Animatoo.animateSlideRight(this)
        }

        btRegister.setOnClickListener {
            val nom = bin.inputUsernameRegisterScreen.text.toString()
            val password = bin.inputPasswordRegisterScreen.text.toString()
            val email = bin.inputEmailRegisterScreen.text.toString()

            if ((email == "") && (password == "") && (nom == "")) {
                layoutEmail.error = getString(R.string.errorNullEmail)
                layoutPassword.error = getString(R.string.errorNullPassword)
                layoutUsername.error = getString(R.string.errorNullUsername)

            } else if ((email == "") && (password == "")) {
                layoutEmail.error = getString(R.string.errorNullEmail)
                layoutEmail.error = getString(R.string.errorNullEmail)
            } else if ((password == "") && (nom == "")) {
                layoutPassword.error = getString(R.string.errorNullPassword)
                layoutUsername.error = getString(R.string.errorNullUsername)
            } else if ((email == "") && (nom == "")) {
                layoutEmail.error = getString(R.string.errorNullEmail)
                layoutUsername.error = getString(R.string.errorNullUsername)

            }else if ((nom == "") && (password == "")){
                layoutPassword.error = getString(R.string.errorNullPassword)
                layoutUsername.error = getString(R.string.errorNullUsername)

            }else if (email==""){
                layoutEmail.error = getString(R.string.errorNullEmail)

            }else if (nom==""){
            layoutUsername.error = getString(R.string.errorNullUsername)

            }else if (password == "") {
            layoutPassword.error = getString(R.string.errorNullPassword)

            }else {
                crearUsuari(nom, password, tvsnackbar)
            }
            Utilities(editTextUsername, editTextPassword, editTextEmail)
        }

    }

    private fun crearUsuari(email: String, password: String, tvsnackbar : TextView) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")


                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                    Animatoo.animateSlideRight(this)

                } else {
                    // If register, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Snackbar.make(tvsnackbar,"Error. Revisa els camps introduits", Snackbar.LENGTH_LONG)
                        .show()

                }
            }
    }

    fun Utilities(edittextUsername: EditText, editTextPassword: EditText, editTextEmail : EditText){

        edittextUsername.clearFocus()
        editTextPassword.clearFocus()
        editTextEmail.clearFocus()
        editTextPassword.text?.clear()

    }





}