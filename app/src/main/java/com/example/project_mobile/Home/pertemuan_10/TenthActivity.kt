package com.example.project_mobile.Home.pertemuan_10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project_mobile.databinding.ActivityTenthBinding
import com.google.android.material.tabs.TabLayoutMediator

class TenthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Setup ViewPager2 Adapter
        val adapter = TenthTabsAdapter(this)
        binding.viewPager.adapter = adapter

        // Hubungkan TabLayout dengan ViewPager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Tab A"
                1 -> "Tab B"
                2 -> "Tab C"
                else -> null
            }
        }.attach()
    }
}