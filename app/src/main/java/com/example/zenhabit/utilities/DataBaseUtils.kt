package com.example.zenhabit.utilities

import android.annotation.SuppressLint
import com.example.zenhabit.classes.DataBase.usersclass.UsersClass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class DataBaseUtils {


    companion object {
        @SuppressLint("StaticFieldLeak")
        val db = FirebaseFirestore.getInstance()
        val user = FirebaseAuth.getInstance().currentUser
        var userData: UsersClass? = null


        fun loadNewUserTask(
            Personalitzada: Boolean,
            data: String,
            nom: String,
            descripcio: String,
            categoria: String,
            indexCategoria : Int
        ) {


            //MIRAR SI ES UNA TASCA O UN "CHALLENGE"
            if (!Personalitzada) {
                val docRef = checkTaskProfile(categoria)
                docRef?.get()?.addOnSuccessListener { documentSnapshot ->
                    userData = documentSnapshot.toObject<UsersClass>()

                    //SET
                    if (userData != null) {
                        userData!!.data = data
                        val documentPath = db.collection("Users").document(user!!.uid)
                            .collection("Challenges").document(/*Introducir nombre de challenge*/)

                        documentPath?.collection(categoria)?.document(categoria)?.set(userData!!)


                    }
                }


            } else if (Personalitzada) {
                    //SET

                    val hashMapDatos = hashMapOf(
                        "nom" to nom,
                        "descripcio" to descripcio,
                        "data" to data,
                        "categoria" to indexCategoria
                    )
                    db.collection("Users").document(user!!.uid).collection("Tasques")
                        .document(nom).set(hashMapDatos)
                }
            }





        fun checkTaskProfile(taskID: String): DocumentReference? {
            var documentReference: DocumentReference? = null
            if (taskID.contains("TS")) {
                documentReference = db.collection("/Perfils/Salut/Tasques").document(taskID)
            } else if (taskID.contains("TP")) {
                documentReference = db.collection("/Perfils/Productivitat/Tasques").document(taskID)
            } else if (taskID.contains("TA")) {
                documentReference = db.collection("/Perfils/Aprenentatge/Tasques").document(taskID)
            }
            return documentReference
        }

        fun updateUserInfo( oldName : String, newName : String, descripcio: String, data : String, categoria: String){
            var deleteRef = db.collection("Users").document(user!!.uid).collection("Tasques").document(oldName)
           var hashMap = hashMapOf(
               "Nombre" to newName,
               "Descripción" to descripcio,
               "Data" to data,
               "Categoria" to categoria

           )
            //Delete old data.
            deleteOldInfo(deleteRef);
            //Set new data.
            db.collection("Users").document(user!!.uid).collection("Tasques").document(newName).set(hashMap)




        }

        private fun deleteOldInfo(deleteRef : DocumentReference ) {
            deleteRef.delete()
        }

    }


}