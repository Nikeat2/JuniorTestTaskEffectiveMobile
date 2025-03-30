package com.example.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.login.databinding.FragmentLoginBinding
import com.example.mainscreen.homepage.view.MainScreenFragment

const val CONTAINER_ID = "CONTAINER_ID"

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var containerID: Int? = null

    private val validationWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            validateForm()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            containerID = it.getInt(CONTAINER_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.putEmailEditText.addTextChangedListener(validationWatcher)
        binding.putPasswordEditText.addTextChangedListener(validationWatcher)

        binding.btnEnter.setOnClickListener {
            if (validateForm()) {
                navigateToMainScreen()
            }
        }

        binding.btnVK.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://vk.com/")
                )
            )
        }

        binding.btnOdnoklassniki.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://ok.ru/")
                )
            )
        }
    }

    private fun validateEmail(): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        val isValid = pattern.matcher(binding.putEmailEditText.text.toString()).matches()

        binding.putEmailEditText.error = if (!isValid) {
            "Некорректный формат электронной почты"
        } else {
            null
        }
        return isValid
    }


    private fun validatePassword(): Boolean {
        val password = binding.putPasswordEditText.text?.toString() ?: ""
        val isValid = password.length >= 8

        binding.putPasswordEditText.error = if (!isValid) {
            "Слишком короткий пароль"
        } else {
            null
        }
        return isValid
    }

    fun validateForm(): Boolean {
        val isEmailValid = validateEmail()
        val isPasswordValid = validatePassword()
        return isEmailValid && isPasswordValid
    }

    private fun navigateToMainScreen() {
        parentFragmentManager.popBackStack()
        parentFragmentManager.beginTransaction()
            .replace(containerID!!, MainScreenFragment.newInstance())
            .commit()
    }

    companion object {
        @JvmStatic
        fun newInstance(containerID: Int) = LoginFragment().apply {
            arguments = Bundle().apply {
                putInt(CONTAINER_ID, containerID)
            }
        }
    }
}