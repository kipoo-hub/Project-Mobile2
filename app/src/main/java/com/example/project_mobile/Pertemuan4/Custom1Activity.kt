package com.example.project_mobile.Pertemuan4

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project_mobile.databinding.ActivityCustom1Binding


class Custom1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityCustom1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCustom1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Menerima data Intent dari DashboardActivity
        val judul = intent.getStringExtra("EXTRA_TITLE")
        val deskripsi = intent.getStringExtra("EXTRA_DESC")

        // Menampilkan data ke UI
        binding.tvJudulHalaman.text = judul ?: "Destinasi Populer"
        binding.tvDeskripsiHalaman.text = deskripsi ?: "Jelajahi dunia bersama kami."

        // Kamu bisa mengganti gambar secara dinamis jika mau:
        // binding.imgDestinasi.setImageResource(R.drawable.nama_gambar_wisata)
    }
}