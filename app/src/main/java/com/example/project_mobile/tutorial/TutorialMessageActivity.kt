package com.example.project_mobile.tutorial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project_mobile.databinding.ActivityTutorialMessageBinding

class TutorialMessageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTutorialMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = TutorialFragmentAdapter(this)
        binding.tutorialMessageViewPager.adapter = adapter

        // Hubungkan DotsIndicator dengan ViewPager2
        binding.dotsIndicator.attachTo(binding.tutorialMessageViewPager)
    }
}