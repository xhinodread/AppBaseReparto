package com.example.appbase.domain

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import javax.inject.Inject

class Estandard @Inject constructor() {

    fun openWhatsAppSend(stringEnvio: String, context: Context): Boolean {
        try {
            val toNumber = "56968549743" // "56991648937" // Replace with mobile phone number without +Sign or leading zeros, but with country code
            val wsIntent = Uri.parse("http://api.whatsapp.com/send?phone=$toNumber&text=${stringEnvio}")
            val mapIntent = Intent(Intent.ACTION_VIEW, wsIntent)
            mapIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            // Try to invoke the intent.
            try {
                context.startActivity(mapIntent)
                return true
            } catch (e: ActivityNotFoundException) {
                Log.d("LOGWS", e.toString())
                return false
            }

        } catch (e: Exception) {
            Log.d("LOGWS", e.toString())
            return false
        }
    }

}