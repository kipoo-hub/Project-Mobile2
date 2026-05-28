package com.example.project_mobile.Profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.project_mobile.AuthActivity
import com.example.project_mobile.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Toolbar agar sinkron dengan BaseActivity
        val activity = requireActivity() as AppCompatActivity
        // Removed binding.toolbar as it's no longer in XML
        activity.supportActionBar?.title = "Profile"

        // Trigger Logout
        binding.btnLogout.setOnClickListener {
            logoutUser()
        }
    }

    private fun logoutUser() {
        // SAMAKAN NAMA FILE DENGAN SPLASH SCREEN ("UserSession")
        val sharedPref = requireActivity().getSharedPreferences("UserSession", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        // Menghapus flag isLogin agar SplashScreen nanti melempar user ke AuthActivity
        editor.clear()
        editor.apply()

        // Berpindah ke AuthActivity
        val intent = Intent(requireContext(), AuthActivity::class.java)

        // Flag ini penting agar user tidak bisa klik "Back" kembali ke halaman Profile
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        startActivity(intent)

        // Menutup BaseActivity
        requireActivity().finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}