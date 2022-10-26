package com.example.zenhabit

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.example.zenhabit.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
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
        val tvsnackbar = bin.tvSnackBar
        val btRegister = bin.btRegisterLoginScreen
        val btForgotPassword = bin.buttonResetPassword
        val btLogin = bin.btLoginLoginScreen

        val edittextUsername = bin.inputUsernameLoginScreen
        val editTextPassword = bin.inputPasswordLoginScreen
        val layoutUsername = bin.usernameLayout
        val layoutPassword = bin.passwordLayout


        auth = Firebase.auth
///////////////////////////////////////////////////////////////////////////////////////////////

        //Focused Listeners

        edittextUsername.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                layoutUsername.error = null
            }
        }

        editTextPassword.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                layoutPassword.error = null
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

            val username = bin.inputUsernameLoginScreen.text.toString().trim()
            val password = bin.inputPasswordLoginScreen.text.toString().trim()

            if ((username == ""))
                layoutUsername.error = getString(R.string.errorNullUsername)

            if (password == "")
                layoutPassword.error = getString(R.string.errorNullPassword)

            if ((username != "") && (password != "")) {

                login(username, password, tvsnackbar, layoutUsername, layoutPassword)
                utilities(edittextUsername, editTextPassword)
            }
        }
////////////////////////////////////////////////////////////////////////////////////////////////////////

    }

    //Functions
    private fun login(email: String, password: String, tvsnackbar:TextView, layoutUsername : TextInputLayout, layoutPassword : TextInputLayout){

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                val firebaseError : String = task.exception.toString()
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")


                    val intent = Intent(this, PasswordCodeRequest::class.java)
                    startActivity(intent)

                    Animatoo.animateSlideLeft(this)


                } else {
                    // If sign in fails, display a message to the user.
                handleErrorsUtility(firebaseError, layoutUsername, layoutPassword, tvsnackbar)
                    Log.d(TAG, "signInWithEmail:error", task.exception)

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
//hol
        }
    }

    private fun handleErrorsUtility(firebaseError : String, layoutUsername : TextInputLayout, layoutPassword: TextInputLayout, tvsnackbar: TextView){

        if (firebaseError.contains("The password is invalid")){
            Snackbar.make(tvsnackbar, getString(R.string.errorWrongPassword), Snackbar.LENGTH_LONG)
                .show()
            layoutPassword.error = getString(R.string.errorWrongPassword)

        }else if(firebaseError.contains("The email address is badly formatted.")){
            Snackbar.make(tvsnackbar,getString(R.string.errorEmailBadFormatted), Snackbar.LENGTH_LONG)
                .show()
            layoutUsername.error = getString(R.string.errorEmailBadFormatted)

        }else if (firebaseError.contains("The supplied auth credential is malformed or has expired.")){
            Snackbar.make(tvsnackbar, getString(R.string.errorInexistentEmail), Snackbar.LENGTH_LONG)
                .show()
            layoutUsername.error = getString(R.string.errorInexistentEmail)
        }else if (firebaseError.contains("There is no user record corresponding to this identifier. The user may have been deleted.")){
            Snackbar.make(tvsnackbar, getString(R.string.errorUnexistentUser),Snackbar.LENGTH_LONG)
                .show()
            layoutUsername.error = getString(R.string.errorUnexistentUser)

        }else if (firebaseError.contains("We have blocked all requests from this device due to unusual activity. Try again later. ")){
            Snackbar.make(tvsnackbar, getString(R.string.errorTempPassLocked), Snackbar.LENGTH_LONG)
                .show()
        }

    }

    private fun utilities(edittextUsername: EditText, editTextPassword: EditText){

        edittextUsername.clearFocus()
        editTextPassword.clearFocus()
        editTextPassword.text?.clear()

    }
////////////////////////////////////////////////////////////////////////////////////////////////////////

}
