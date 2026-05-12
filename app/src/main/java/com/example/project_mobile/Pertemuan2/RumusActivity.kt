package com.example.project_mobile.Pertemuan2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project_mobile.databinding.ActivityRumusBinding

class RumusActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRumusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inisialisasi Binding
        binding = ActivityRumusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- MENERIMA DATA DARI INTENT ---
        // Gunakan key yang sama dengan yang ada di DashboardActivity
        val judul = intent.getStringExtra("EXTRA_TITLE")
        val deskripsi = intent.getStringExtra("EXTRA_DESC")

        // Set data ke TextView
        binding.tvJudulHalaman.text = judul ?: "Tidak ada judul"
        binding.tvDeskripsiHalaman.text = deskripsi ?: "Tidak ada deskripsi"
    }
}