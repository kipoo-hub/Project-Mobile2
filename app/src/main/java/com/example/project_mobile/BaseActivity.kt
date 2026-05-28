package com.example.project_mobile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.project_mobile.About.AboutFragment
import com.example.project_mobile.Message.MessageFragment
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
            val fragment = when (item.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_about -> AboutFragment()
                R.id.nav_message -> MessageFragment()
                R.id.nav_profile -> ProfileFragment()
                else -> null
            }

            fragment?.let {
                // Mengambil title langsung dari menu item yang diklik
                replaceFragment(it, item.title.toString())
                true
            } ?: false
        }
    }

    // Fungsi Replace Fragment
    private fun replaceFragment(fragment: Fragment, title: String) {
        // Update judul di kedua tempat untuk memastikan sinkronisasi visual
        binding.toolbar.title = title
        supportActionBar?.title = title

        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}