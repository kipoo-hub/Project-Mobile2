// 1. Sesuaikan package dengan folder tempat file ini berada (Pertemuan3)
package com.example.project_mobile.Pertemuan3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
// 2. IMPORT YANG BENAR: Pastikan hanya mengimpor dari project_mobile
import com.example.project_mobile.databinding.ActivityThirdBinding
// Perhatikan: Pastikan DashboardActivity juga sudah dipindah ke project_mobile
import com.example.project_mobile.Pertemuan4.DashboardActivity

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // Setup Binding
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                // Berpindah ke DashboardActivity di dalam project_mobile
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)

                Toast.makeText(this, "Welcome to Mata Cantik Travel!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                if (username.isEmpty()) binding.etUsername.error = "Masukkan Username/Email"
                if (password.isEmpty()) binding.etPassword.error = "Masukkan Password"

                Toast.makeText(this, "Harap isi semua kolom!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}