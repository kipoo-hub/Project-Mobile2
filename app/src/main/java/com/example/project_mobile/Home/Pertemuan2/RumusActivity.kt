package com.example.project_mobile.Home.Pertemuan2

import android.os.Bundle
import android.widget.Toast
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
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // --- MENERIMA DATA DARI INTENT ---
        val judul = intent.getStringExtra("EXTRA_TITLE")
        val deskripsi = intent.getStringExtra("EXTRA_DESC")

        // Set data ke TextView
        binding.tvJudulHalaman.text = judul ?: "Kalkulator Rumus"
        binding.tvDeskripsiHalaman.text = deskripsi ?: "Pilih rumus yang ingin Anda hitung"


        // --- LOGIKA HITUNG LUAS SEGITIGA ---
        binding.btnSegitiga.setOnClickListener {
            val inputAlas = binding.etAlas.text.toString()
            val inputTinggi = binding.etTinggi.text.toString()

            if (inputAlas.isNotEmpty() && inputTinggi.isNotEmpty()) {
                val alas = inputAlas.toDouble()
                val tinggi = inputTinggi.toDouble()

                // Rumus: 0.5 * alas * tinggi
                val luas = 0.5 * alas * tinggi

                binding.tvHasil.text = "Luas Segitiga:\n0.5 x $alas x $tinggi = $luas"
            } else {
                Toast.makeText(this, "Mohon isi alas dan tinggi terlebih dahulu!", Toast.LENGTH_SHORT).show()
            }
        }


        // --- LOGIKA HITUNG VOLUME KUBUS ---
        binding.btnKubus.setOnClickListener {
            val inputSisi = binding.etSisi.text.toString()

            if (inputSisi.isNotEmpty()) {
                val sisi = inputSisi.toDouble()

                // Rumus: sisi * sisi * sisi
                val volume = sisi * sisi * sisi

                binding.tvHasil.text = "Volume Kubus:\n$sisi x $sisi x $sisi = $volume"
            } else {
                Toast.makeText(this, "Mohon isi panjang sisi terlebih dahulu!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}