package com.example.tenmintshop.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.tenmintshop.R
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
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private var firebaseAuthInstanse: FirebaseAuth? = null

    fun getAuthInstanse(): FirebaseAuth {
        if (firebaseAuthInstanse == null) {
            firebaseAuthInstanse = FirebaseAuth.getInstance()
        }
        return firebaseAuthInstanse!!
    }

    fun getCurrentUserId(): String {
        return FirebaseAuth.getInstance().currentUser!!.uid
    }

    fun getUserPhoneNumber(): String {
        return FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString()
    }

    fun setStatusBarColor(context: Context , activity : Activity){
        activity?.window?.apply {
            val statusBarColors = ContextCompat.getColor(context, R.color.yellow)
            statusBarColor = statusBarColors
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
    }
}