package com.example.tenmintshop.fragment.signin

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tenmintshop.R
import com.example.tenmintshop.databinding.FragmentSigninBinding
import com.example.tenmintshop.utils.Utils

class SigninFragment : Fragment() {

    private lateinit var binding: FragmentSigninBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSigninBinding.inflate(inflater, container, false)

        activity?.let { Utils. setStatusBarColor(requireContext() , it) }
        // for get user data
        getUserNumber()
        // on continue button click for get user details for this user time

        onContinueButton()
        return binding.root
    }

    private fun onContinueButton() {
        binding.loginBtnContinue.setOnClickListener {
            val number = binding.etUserNumber.text.toString()
            if (number.isEmpty() || number.length != 10) {
                Utils.showToast(requireContext(), "check your mobile number")
            } else {
                val bundle = Bundle()
                bundle.putString("number", number)
                findNavController().navigate(R.id.action_signinFragment_to_otpFragment, bundle)
            }
        }
    }

    private fun getUserNumber() {
        binding.etUserNumber.addTextChangedListener (
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(
                    number: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    val len = number?.length

                    if (len == 10) {
                        binding.loginBtnContinue.setBackgroundColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.green
                            )
                        )
                    } else {
                        binding.loginBtnContinue.setBackgroundColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.grayish_blue
                            )
                        )
                    }
                }

                override fun afterTextChanged(s: Editable?) {

                }

            }
        )

        }





}


