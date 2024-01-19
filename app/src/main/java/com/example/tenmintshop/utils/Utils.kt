package com.example.tenmintshop.utils

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.tenmintshop.databinding.ProgrssDialogBinding
import com.google.firebase.auth.FirebaseAuth

object Utils {

    private var dialog: AlertDialog? = null

    fun showDialog(context: Context, message: String) {

        val progress = ProgrssDialogBinding.inflate(LayoutInflater.from(context))
        progress.tvMessage.text = message
        dialog = AlertDialog.Builder(context).setView(progress.root).setCancelable(false).create()
        dialog!!.show()
    }

    fun hideDialog() {
        dialog?.dismiss()
    }

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, "", Toast.LENGTH_SHORT).show()
    }

    private var firebaseAuthInstanse: FirebaseAuth? = null

    fun getAuthInstanse(): FirebaseAuth {
        if (firebaseAuthInstanse == null) {
            firebaseAuthInstanse = FirebaseAuth.getInstance()
        }
        return firebaseAuthInstanse!!
    }


}