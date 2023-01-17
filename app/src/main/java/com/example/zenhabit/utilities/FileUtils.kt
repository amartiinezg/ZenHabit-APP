package com.example.zenhabit.utilities

import android.content.Context
import android.widget.Toast

class FileUtils {
    /**
    * Mostra una notificació toast a la pantalla.
    * @param context Context en què es mostrarà la notificació.
    * @param text Text que es mostrarà a la notificació.
    * @param lenght Durada de la notificació.
     */
    fun showToast (context : Context, text : String, lenght : Int){
        Toast.makeText(context, text, lenght)
    }


    /**
     * Elimina els espais en blanc al principi i al final d'una cadena de text.
     * @param text Text del qual s'eliminaran els espais en blanc.
     * @return Cadena de text sense espais en blanc al principi i al final.
     */
    fun removeBlankSpaces (text : String){
        text.trim()
    }
}