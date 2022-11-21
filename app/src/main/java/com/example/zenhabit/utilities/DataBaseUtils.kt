package com.example.zenhabit.utilities

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DataBaseUtils {
    val db = Firebase.firestore


    companion object {
        val db = Firebase.firestore
       fun createTrees()
        {
            val abeto = hashMapOf(
                "name" to "Abeto",
                "icon" to ""

            )

            val cerezo = hashMapOf(
                "name" to "cerezo",
                "icon" to ""
            )


            //Sets de base de datos.
            db.collection("Jardi").document("Arbres").set(abeto)
            db.collection("Jardi").document("Arbres").set(cerezo)
        }
    }
}