package com.example.project_mobile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.project_mobile.About.AboutFragment
import com.example.project_mobile.databinding.ActivityBaseBinding
import com.example.project_mobile.Home.HomeFragment
import com.example.project_mobile.Profile.ProfileFragment

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Terapkan Toolbar sebagai ActionBar resmi
        // Pastikan Anda sudah mengubah tema ke .NoActionBar di themes.xml
        setSupportActionBar(binding.toolbar)

        // 2. Tampilkan HomeFragment secara default saat pertama kali dibuka
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment(), "Home")
        }

        // 3. Listener klik Bottom Navigation
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment(), "Home")
                    true
                }
                R.id.nav_about -> {
                    replaceFragment(AboutFragment(), "About")
                    true
                }
                R.id.nav_profile -> {
                    replaceFragment(ProfileFragment(), "Profile")
                    true
                }
                else -> false
            }
        }
    }

    // Fungsi Replace Fragment
    private fun replaceFragment(fragment: Fragment, title: String) {
        // Mengubah judul pada Toolbar secara dinamis
        supportActionBar?.title = title

        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            // .addToBackStack(null) // Opsional: Jangan gunakan ini pada BottomNav agar tidak membingungkan user
            .commit()
    }
}