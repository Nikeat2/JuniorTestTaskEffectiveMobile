package com.example.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.login.LoginFragment
import com.example.onboarding.databinding.FragmentOnBoardingBinding

const val CONTAINER_ID = "CONTAINER_ID"

class OnBoardingFragment : Fragment() {

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding get() = _binding!!
    private var containerID: Int? = null

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
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.continueButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(containerID!!, LoginFragment.newInstance(containerID!!))
                .addToBackStack("OnBoarding").commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(containerId: Int) = OnBoardingFragment().apply {
            arguments = Bundle().apply {
                putInt(CONTAINER_ID, containerId)
            }
        }
    }
}