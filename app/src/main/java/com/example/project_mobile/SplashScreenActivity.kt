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

        Handler(Looper.getMainLooper()).postDelayed({
            // Implementasi alur image_1cc141.png
            val isLogin = sharedPref.getBoolean("isLogin", false)

            if (isLogin) {
                // User sudah login -> MainActivity
                startActivity(Intent(this, BaseActivity::class.java))
            } else {
                // User belum login -> AuthActivity
                startActivity(Intent(this, AuthActivity::class.java))
            }
            finish()
        }, 3000)
    }
}