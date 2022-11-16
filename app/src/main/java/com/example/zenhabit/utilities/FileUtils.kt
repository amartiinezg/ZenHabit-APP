package com.example.zenhabit.utilities

import android.content.Context
import android.widget.Toast

class FileUtils {
    fun showToast (context : Context, text : String, lenght : Int){
        Toast.makeText(context, text, lenght)
    }

    fun removeBlankSpaces (text : String){
        text.trim()
    }
}