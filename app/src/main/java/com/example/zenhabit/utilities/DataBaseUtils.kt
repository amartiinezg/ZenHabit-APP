package com.example.zenhabit.utilities

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DataBaseUtils {



    companion object {
        val db = Firebase.firestore
       fun createJardi()
        {
            val pino = hashMapOf(
                "Name" to "Abeto",
                "Icon" to "",
                "Type" to "Arbol"
            )

            val cerezo = hashMapOf(
                "Name" to "Cerezo",
                "Icon" to "",
                "Type" to "Arbol"
            )


            //Sets de base de datos.
            db.collection("Jardi").document("Pino").set(pino)
            db.collection("Jardi").document("Cerezo").set(cerezo)

        }

        fun createPerfils(){

            val test1 = hashMapOf(
                "test1" to "test1"

            )
            //Sets de base de datos.
            db.collection("Perfils").document("Salut").set(test1)
            db.collection("Perfils").document("Aprenentatge").set(test1)
            db.collection("Perfils").document("Productivitat").set(test1)

        }

        fun createTasques(){

        }
    }
}