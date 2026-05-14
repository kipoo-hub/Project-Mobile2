package com.example.project_mobile.Home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.project_mobile.Home.Pertemuan2.RumusActivity
import com.example.project_mobile.Home.Pertemuan3.ThirdActivity
import com.example.project_mobile.Home.Pertemuan3.ThirdResultActivity
import com.example.project_mobile.Home.Pertemuan4.DashboardActivity
import com.example.project_mobile.Home.Pertemuan5.WebViewActivity
import com.example.project_mobile.R
import com.example.project_mobile.SplashScreenActivity
import com.example.project_mobile.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Pastikan Toolbar di XML FragmentHome menggunakan ID yang berbeda (misal: toolbarHome)
        // jika BaseActivity sudah memiliki toolbar utama untuk menghindari konflik.
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar)
        activity.supportActionBar?.title = "Home"

        // Setup Listener Klik untuk Navigasi
        setupNavigation()
    }

    private fun setupNavigation() {
        // Pertemuan 2 - Rumus Kalkulator
        binding.btnToSecond.setOnClickListener {
            startActivity(Intent(requireContext(), RumusActivity::class.java))
        }

        // Pertemuan 3 - Intent & Activity
        binding.btnToThird.setOnClickListener {
            startActivity(Intent(requireContext(), ThirdResultActivity::class.java))
        }

        // Pertemuan 4 - Marketplace Travel
        binding.btnToFourth.setOnClickListener {
            startActivity(Intent(requireContext(), DashboardActivity::class.java))
        }

        // Pertemuan 5 - Web View
        binding.btnToFifth.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }

        // Pertemuan 6 - Fragment & Splash
        binding.btnToSixth.setOnClickListener {
            startActivity(Intent(requireContext(), SplashScreenActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}