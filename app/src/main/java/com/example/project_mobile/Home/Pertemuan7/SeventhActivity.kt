package com.example.project_mobile.Home.Pertemuan7

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.project_mobile.Home.HomeFragment
import com.example.project_mobile.Profile.ProfileFragment
import com.example.project_mobile.R
import com.example.project_mobile.databinding.ActivitySeventhBinding

class SeventhActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeventhBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeventhBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar sesuai tema Bina Desa
        binding.toolbar.setNavigationOnClickListener { finish() }

        // Load Fragment pertama secara default
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        // Navigasi antar Fragment
        binding.btnHome.setOnClickListener { replaceFragment(HomeFragment()) }
        binding.btnProfile.setOnClickListener { replaceFragment(ProfileFragment()) }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}