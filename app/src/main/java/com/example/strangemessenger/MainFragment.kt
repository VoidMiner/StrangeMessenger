package com.example.strangemessenger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.strangemessenger.databinding.FragmentMainBinding
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInClient


class MainFragment : Fragment() {
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.logoutButton.setOnClickListener {
            // Выполните разлогинивание пользователя (Google Sign-Out)
            googleSignInClient.signOut().addOnCompleteListener {
                // После разлогинивания, выполните навигацию к экрану авторизации
                findNavController().navigate(R.id.action_mainFragment_to_authFragment)
            }
        }

        return binding.root
    }
    // Метод для передачи GoogleSignInClient из AuthFragment
    fun setGoogleSignInClient(googleSignInClient: GoogleSignInClient) {
        this.googleSignInClient = googleSignInClient
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Получите googleSignInClient из Safe Args
        arguments?.let {
            googleSignInClient = MainFragmentArgs.fromBundle(it).googleSignInClient
        }
    }*/
}

