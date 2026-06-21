package com.example.project_mobile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.project_mobile.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = getSharedPreferences("UserSession", Context.MODE_PRIVATE)
        
        // RESET PAKSA (Hapus baris ini setelah Onboarding muncul sekali)
        // sharedPref.edit().putBoolean("onboardingFinished", false).apply()

        Handler(Looper.getMainLooper()).postDelayed({

            val isLogin = sharedPref.getBoolean("isLogin", false)

            if (isLogin) {
                startActivity(Intent(this, BaseActivity::class.java))
            } else {
                val onboardingFinished = sharedPref.getBoolean("onboardingFinished", false)
                if (onboardingFinished) {
                    startActivity(Intent(this, AuthActivity::class.java))
                } else {
                    // Jika belum pernah onboarding, arahkan ke Tutorial
                    startActivity(Intent(this, com.example.project_mobile.tutorial.TutorialMessageActivity::class.java))
                }
            }
            finish()
        }, 3000)
    }
}