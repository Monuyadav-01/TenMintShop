package com.example.tenmintshop.fragment.signin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tenmintshop.R
import com.example.tenmintshop.databinding.FragmentOtpBinding
import com.example.tenmintshop.models.Users
import com.example.tenmintshop.utils.Utils
import com.example.tenmintshop.viewmodels.AuthViewModel
import kotlinx.coroutines.launch


class OtpFragment : Fragment() {

    private lateinit var binding: FragmentOtpBinding
    private lateinit var userNumber: String
    private val viewModel: AuthViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOtpBinding.inflate(inflater, container, false)

        getUserNumber()
        customizingEnteringOTP()
        sendOTP()
        onOTPButtonClick()
        onBackButtonClick()

        return binding.root


    }

    private fun onOTPButtonClick() {
        binding.verifyBtn.setOnClickListener {
            Utils.showDialog(requireContext(), "Signing You .....")
            val editTexts = arrayOf(
                binding.etOtp1,
                binding.etOtp2,
                binding.etOtp3,
                binding.etOtp4,
                binding.etOtp5,
                binding.etOtp6
            )
            val otp = editTexts.joinToString("") { it.text.toString() }

            if (otp.length < editTexts.size) {
                Utils.showToast(requireContext(), "enter correct otp")
            } else {
                editTexts.forEach {
                    it.text?.clear();
                    it.clearFocus()
                }
                verifyOtp(otp)
            }
        }
    }

    private fun verifyOtp(otp: String) {

        val user = Users(uid = Utils.getCurrentUserId(), userNumber, userAddress = null)

        viewModel.signInWithPhoneAuthCredential(otp, userNumber , user)

        lifecycleScope.launch {
            viewModel.isSignedSuccessFully.collect {
                if (it) {
                    Utils.showToast(requireContext(), "signin successfully")
                    Utils.hideDialog()
                } else {

                }
            }
        }
    }


    private fun sendOTP() {
        Utils.showDialog(requireContext(), "Sending OTP .....")

        viewModel.apply {
            sendOtp(userNumber, requireActivity())
            lifecycleScope.launch {
                otpSent.collect { otpSent ->
                    if (otpSent) {
                        Utils.hideDialog()
                        Utils.showToast(requireContext(), "Otp Sent to Your Number")
                    }
                }
            }

        }
    }

    private fun onBackButtonClick() {
        binding.tbOtpFragment.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_otpFragment_to_signinFragment)
        }
    }

    private fun customizingEnteringOTP() {
        val editTexts = arrayOf(
            binding.etOtp1,
            binding.etOtp2,
            binding.etOtp3,
            binding.etOtp4,
            binding.etOtp5,
            binding.etOtp6
        )
        for (i in editTexts.indices) {
            editTexts[i].addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {

                }

                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1) {
                        if (i < editTexts.size - 1) {
                            editTexts[i + 1].requestFocus()
                        }
                    } else if (s?.length == 0) {
                        if (i > 0) {
                            editTexts[i - 1].requestFocus()
                        }
                    }
                }

            })

        }
    }


    private fun getUserNumber() {
        val bundle = arguments
        userNumber = bundle?.getString("number").toString()
        binding.tvUserNumber.text = userNumber
    }

}