package com.example.strangemessenger

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.strangemessenger.databinding.FragmentAuthBinding
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


class AuthFragment : Fragment() {

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var binding : FragmentAuthBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Инициализация ViewBinding
        binding = FragmentAuthBinding.inflate(inflater, container, false)

        // Обработка нажатий кнопок для входа
        binding.googleLoginButton.setOnClickListener {
            // код для входа через Google
        }

        binding.logoutButton.setOnClickListener {
            // Ваш код для разлогинивания пользователя
            // ...

            // После разлогинивания, выполните навигацию к экрану авторизации
            findNavController().navigate(R.id.action_mainFragment_to_authFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        // Обработка нажатия кнопки входа через Google
        binding.googleLoginButton.setOnClickListener {
            signInWithGoogle()
        }
    }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    companion object {
        private const val RC_SIGN_IN = 123
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val result = data?.let { Auth.GoogleSignInApi.getSignInResultFromIntent(it) }
            if (result != null) {
                if (result.isSuccess) {
                    // Успешный вход через Google, выполните навигацию к главному экрану
                    findNavController().navigate(R.id.action_authFragment_to_mainFragment)
                } else {
                    // Обработка ошибки входа
                    handleError(result.status.toString())
                }
            } else {
                // Обработка случая, когда result == null
                handleError("SignInResult is null")
            }
        }
    }

    private fun handleError(errorMessage: String) {
        // Здесь вы можете обработать ошибку, например, показать Toast или логировать
        // Помните, что это пример, и вам может потребоваться более подробная обработка ошибок в вашем реальном приложении
        Log.e(TAG, "Error during sign in: $errorMessage")
        // Показать Toast с сообщением об ошибке
        Toast.makeText(requireContext(), "Error during sign in: $errorMessage", Toast.LENGTH_SHORT).show()
    }

}
