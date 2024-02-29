package com.example.tenmintshop.fragment.signin

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tenmintshop.R
import com.example.tenmintshop.activity.UsersMainActivity
import com.example.tenmintshop.databinding.FragmentSplashBinding
import com.example.tenmintshop.utils.Utils
import com.example.tenmintshop.viewmodels.AuthViewModel
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        Utils.setStatusBarColor(requireContext(), requireActivity())

        binding.startButton.setOnClickListener {
            lifecycleScope.launch {
                viewModel.isCurrentUser.collect { isLoggedIn ->
                    if (isLoggedIn) {
                        startActivity(Intent(requireActivity(), UsersMainActivity::class.java))
                        requireActivity().finish()
                    } else {
                        findNavController().navigate(R.id.action_splashFragment_to_signinFragment)
                    }
                }
            }
        }


//        Handler(Looper.getMainLooper()).postDelayed({
//
//        }, 1000)

        return binding.root
    }
}
