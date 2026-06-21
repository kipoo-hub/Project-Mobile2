package com.example.project_mobile.tutorial

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project_mobile.AuthActivity
import com.example.project_mobile.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment() {
    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(title: String, description: String, backgroundColor: Int, isLastPage: Boolean = false): OnboardingFragment {
            val fragment = OnboardingFragment()
            val args = Bundle()
            args.putString("title", title)
            args.putString("description", description)
            args.putInt("bgColor", backgroundColor)
            args.putBoolean("isLast", isLastPage)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val title = arguments?.getString("title")
        val description = arguments?.getString("description")
        val bgColor = arguments?.getInt("bgColor") ?: 0
        val isLast = arguments?.getBoolean("isLast") ?: false

        binding.tvTitle.text = title
        binding.tvDescription.text = description
        binding.onboardingBg.setBackgroundColor(bgColor)

        if (isLast) {
            binding.btnStart.visibility = View.VISIBLE
            binding.btnStart.setOnClickListener {
                // Save onboarding status
                val sharedPref = requireContext().getSharedPreferences("UserSession", android.content.Context.MODE_PRIVATE)
                sharedPref.edit().putBoolean("onboardingFinished", true).apply()

                startActivity(Intent(requireContext(), AuthActivity::class.java))
                activity?.finish()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}