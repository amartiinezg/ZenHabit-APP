package com.example.zenhabit.utilities

import com.example.zenhabit.classes.DataBase.usersclass.UsersClass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class DataBaseUtils() {


    companion object {
        val db = FirebaseFirestore.getInstance()
        val user = FirebaseAuth.getInstance().currentUser
        var userData: UsersClass? = null;

         fun loadAllUserData(taskID: String){
             if (taskID.contains("TS")){
                 val docRef = db.collection("/Perfils/Salut/Tasques/").document(taskID)
                 docRef.get().addOnSuccessListener { documentSnapshot ->
                     userData = documentSnapshot.toObject<UsersClass>()
                 }
             }
         }

    }


}