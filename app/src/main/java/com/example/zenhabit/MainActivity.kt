package com.example.zenhabit

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.example.zenhabit.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {


    private lateinit var bin: ActivityMainBinding
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)

        //Binding Variables
        bin =   ActivityMainBinding.inflate(layoutInflater)
        setContentView(bin.root)
        /////////////////////////////////////////////////////////////////////

        //Declarated Variables
        var tvsnackbar = bin.tvSnackBar
        val btRegister = bin.btRegisterLoginScreen
        val btForgotPassword = bin.buttonResetPassword
        val btLogin = bin.btLoginLoginScreen

        var edittextUsername = bin.inputUsernameLoginScreen
        var editTextPassword = bin.inputPasswordLoginScreen
        var layoutUsername = bin.usernameLayout
        var layoutPassword = bin.passwordLayout


        auth = Firebase.auth
///////////////////////////////////////////////////////////////////////////////////////////////

        //Focused Listeners

        edittextUsername.onFocusChangeListener = OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                layoutUsername.error = null
            } else {

            }
        }

        editTextPassword.onFocusChangeListener = OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                layoutPassword.error = null
            } else {

            }
        }
////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Button Listeners
        btRegister.setOnClickListener{
            val intent = Intent(this, RegistreActivity::class.java)
            startActivity(intent)

            Animatoo.animateSlideLeft(this)
        }

        btForgotPassword.setOnClickListener{
            val intent = Intent(this, PasswordCodeRequest::class.java)
            startActivity(intent)

            Animatoo.animateSlideLeft(this)
        }

        btLogin.setOnClickListener {

            val username = bin.inputUsernameLoginScreen.text.toString()
            val password = bin.inputPasswordLoginScreen.text.toString()

            if ((username == "") && (password == "")) {
                layoutUsername.error = getString(R.string.errorNullUsername)
                layoutPassword.error = getString(R.string.errorNullPassword)

            } else if (username == "") {
                layoutUsername.error = getString(R.string.errorNullUsername)
            } else if (password == "") {
                layoutPassword.error = getString(R.string.errorNullPassword)
                layoutPassword.startIconDrawable


            } else {
                login(username, password, tvsnackbar)
            }
            Utilities(edittextUsername, editTextPassword)

        }
////////////////////////////////////////////////////////////////////////////////////////////////////////

    }

    //Functions
    private fun login(email: String, password: String, tvsnackbar:TextView){

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")


                    val intent = Intent(this, PasswordCodeRequest::class.java)
                    startActivity(intent)

                    Animatoo.animateSlideLeft(this)


                } else  {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Snackbar.make(tvsnackbar,"El Usuari/Mail o la contrasenya son incorrectes.",Snackbar.LENGTH_LONG)
                        .show()



                }
            }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){

             val intent = Intent(this,RegistreActivity::class.java)
            startActivity(intent)

        }else{

        }
    }

    fun Utilities(edittextUsername: EditText, editTextPassword: EditText){

        edittextUsername.clearFocus()
        editTextPassword.clearFocus()
        editTextPassword.text?.clear()

    }
////////////////////////////////////////////////////////////////////////////////////////////////////////

}
