package com.example.tenmintshop.viewmodels

import android.app.Activity
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.tenmintshop.models.Users
import com.example.tenmintshop.utils.Utils
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.concurrent.TimeUnit

class AuthViewModel : ViewModel() {
    private val _verificationId = MutableStateFlow<String?>(null)

    private val _otpSent = MutableStateFlow(false)
    val otpSent = _otpSent

    private var _isSignedSuccessFully = MutableStateFlow(false)
    val isSignedSuccessFully = _isSignedSuccessFully


    private var _isCurrentUser = MutableStateFlow(false)
    var isCurrentUser = _isCurrentUser


    init {

        Utils.getAuthInstanse().currentUser?.let {
            _isCurrentUser.value = true
        }
    }

    fun sendOtp(userNumber: String, activity: Activity) {

        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {

            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.w(TAG, "onVerificationFailed", e)

                if (e is FirebaseAuthInvalidCredentialsException) {

                } else if (e is FirebaseTooManyRequestsException) {

                } else if (e is FirebaseAuthMissingActivityForRecaptchaException) {

                }

                // Show a message and update the UI
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken,
            ) {
                _verificationId.value = verificationId
                _otpSent.value = true
            }


        }
        val options = PhoneAuthOptions.newBuilder(Utils.getAuthInstanse())
            .setPhoneNumber("+91$userNumber") // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

    }

    fun signInWithPhoneAuthCredential(otp: String, userNumber: String, user: Users) {
        val credential = PhoneAuthProvider.getCredential(_verificationId.value.toString(), otp)
        Utils.getAuthInstanse().signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    FirebaseDatabase.getInstance().getReference("ALL USERS").child("USERS")
                        .child(Utils.getUserPhoneNumber()).setValue(user)
                    _isSignedSuccessFully.value = true

                } else {

                }
            }
    }


}