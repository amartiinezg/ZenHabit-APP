package com.example.zenhabit.activities

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.example.zenhabit.R
import com.example.zenhabit.databinding.ActivityRegistreBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegistreActivity : AppCompatActivity() {
    private lateinit var bin: ActivityRegistreBinding
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registre)
        auth = Firebase.auth
        bin =   ActivityRegistreBinding.inflate(layoutInflater)
        setContentView(bin.root)

        val btCancel = bin.btCancelRegisterScreen
        val btRegister = bin.btRegister
        val editTextUsername = bin.inputUsernameRegisterScreen
        val editTextPassword = bin.inputPasswordRegisterScreen
        val editTextEmail = bin.inputEmailRegisterScreen
        val layoutUsername = bin.usernameLayoutRegister
        val layoutPassword = bin.passwordLayoutRegister
        val layoutEmail = bin.emailLayout
        val tvsnackbar = bin.snackbarRegister

        //Focused Listeners

        editTextUsername.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                layoutUsername.error = null
            }
        }

        editTextPassword.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                layoutPassword.error = null
            }
        }

        editTextEmail.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                layoutEmail.error = null
            }
        }


        btCancel.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            Animatoo.animateSlideRight(this)
        }

        btRegister.setOnClickListener {
            val nom = bin.inputUsernameRegisterScreen.text.toString().trim()
            val password = bin.inputPasswordRegisterScreen.text.toString()
            val email = bin.inputEmailRegisterScreen.text.toString().trim()

           if (email == "")
               layoutEmail.error = getString(R.string.errorNullEmail)

            if (nom == "")
                layoutUsername.error = getString(R.string.errorNullUsername)

            if (password == "")
                layoutPassword.error = getString(R.string.errorNullPassword)

            if (email != "" && password != "" && nom != "")
                crearUsuari(email, password, tvsnackbar, layoutEmail, layoutPassword)
            utilities(editTextUsername, editTextPassword, editTextEmail)
        }

    }

    private fun crearUsuari(username: String, password: String, tvsnackbar : TextView, emailLayout: TextInputLayout, passwordLayout: TextInputLayout) {

        auth.createUserWithEmailAndPassword(username, password)
            .addOnCompleteListener(this) { task ->
                val firebaseError = task.exception.toString()

                if (task.isSuccessful) {
                    val user = FirebaseAuth.getInstance().currentUser
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")

                    //Crear documentos de base de datos en firestore.

                    val Users = hashMapOf(
                        "test" to "test",
                        "born" to 1815
                    )

                    db.collection("Users").document(user!!.uid).set(Users)



                    val intent = Intent(this, acitivity_menuNavigation::class.java)
                    startActivity(intent)

                    Animatoo.animateSlideLeft(this)




                } else {
                    // If register, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                   handleErrorsUtility(firebaseError,emailLayout, passwordLayout, tvsnackbar )

                }
            }
    }

    private fun utilities(edittextUsername: EditText, editTextPassword: EditText, editTextEmail : EditText){

        edittextUsername.clearFocus()
        editTextPassword.clearFocus()
        editTextEmail.clearFocus()
        editTextPassword.text?.clear()

    }

    private fun handleErrorsUtility(firebaseError : String, emailLayout : TextInputLayout, layoutPassword: TextInputLayout, tvsnackbar: TextView){

        if (firebaseError.contains("The password is invalid")){
            Snackbar.make(tvsnackbar, getString(R.string.errorWrongPassword), Snackbar.LENGTH_LONG)
                .show()
            layoutPassword.error = getString(R.string.errorWrongPassword)

        }else if(firebaseError.contains("The email address is badly formatted.")){
            Snackbar.make(tvsnackbar,getString(R.string.errorEmailBadFormatted), Snackbar.LENGTH_LONG)
                .show()
            emailLayout.error = getString(R.string.errorEmailBadFormatted)

        }else if (firebaseError.contains("The supplied auth credential is malformed or has expired.")){
            Snackbar.make(tvsnackbar, getString(R.string.errorInexistentEmail), Snackbar.LENGTH_LONG)
                .show()
           emailLayout.error = getString(R.string.errorInexistentEmail)
        }else if (firebaseError.contains("There is no user record corresponding to this identifier. The user may have been deleted.")){
            Snackbar.make(tvsnackbar, getString(R.string.errorUnexistentUser),Snackbar.LENGTH_LONG)
                .show()
           emailLayout.error = getString(R.string.errorUnexistentUser)

        }else if (firebaseError.contains("We have blocked all requests from this device due to unusual activity. Try again later. ")){
            Snackbar.make(tvsnackbar, getString(R.string.errorTempPassLocked), Snackbar.LENGTH_LONG)
                .show()
        }else if (firebaseError.contains("The email address is already in use by another account.")){
            Snackbar.make(tvsnackbar, getString(R.string.errorEmailAlreadyInUse), Snackbar.LENGTH_LONG)
                .show()
            emailLayout.error = getString(R.string.errorEmailAlreadyInUse)
        }

    }





}