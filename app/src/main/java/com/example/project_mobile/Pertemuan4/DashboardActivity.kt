package com.example.project_mobile.Pertemuan4

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project_mobile.Pertemuan2.RumusActivity
import com.example.project_mobile.Pertemuan3.ThirdActivity
import com.example.project_mobile.databinding.ActivityDashboardBinding
import com.google.android.material.snackbar.Snackbar

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Memastikan tampilan memenuhi layar (Edge-to-Edge)
        enableEdgeToEdge()

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengatur padding agar tidak tertutup Status Bar/Nav Bar
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- Logika Navigasi Layanan Travel ---

        // Tombol 1: Rumus Bangun Ruang (Layanan Utama)
        binding.btnRumus.setOnClickListener {
            pindahHalaman(
                "Rumus Bangun Ruang",
                "Pelajari berbagai rumus matematika bangun ruang untuk perjalanan edukasimu.",
                RumusActivity::class.java
            )
        }

        // Tombol 2: Custom 1 (Layanan Tambahan)
        binding.btnCustom1.setOnClickListener {
            pindahHalaman(
                "Destinasi Populer",
                "Temukan tempat-tempat menarik yang paling sering dikunjungi.",
                Custom1Activity::class.java
            )
        }

        // Tombol 3: Custom 2 (Layanan Tambahan)
        binding.btnCustom2.setOnClickListener {
            pindahHalaman(
                "Promo Eksklusif",
                "Dapatkan diskon khusus untuk pengguna setia Mata Cantik Travel.",
                Custom2Activity::class.java
            )
        }

        // Tombol 4: Logout (Fitur Keamanan)
        binding.btnLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    /**
     * Fungsi Helper untuk berpindah halaman sambil membawa data (Intent Extra)
     */
    private fun pindahHalaman(judul: String, deskripsi: String, tujuan: Class<*>) {
        val intent = Intent(this, tujuan)
        // Pastikan KEY ini ("EXTRA_TITLE" & "EXTRA_DESC") sama dengan yang dipanggil di halaman tujuan
        intent.putExtra("EXTRA_TITLE", judul)
        intent.putExtra("EXTRA_DESC", deskripsi)
        startActivity(intent)
    }

    /**
     * Menampilkan dialog konfirmasi sebelum keluar aplikasi
     */
    private fun showLogoutDialog() {
        AlertDialog.Builder(this).apply {
            setTitle("Konfirmasi Logout")
            setMessage("Apakah Anda yakin ingin keluar dari Mata Cantik Travel?")
            setPositiveButton("Iya") { _, _ ->
                // Kembali ke halaman Login (MainActivity)
                val intent = Intent(this@DashboardActivity, ThirdActivity::class.java)
                startActivity(intent)
                finish() // Hapus Dashboard dari stack agar tidak bisa di-Back
            }
            setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
                // Menampilkan Snackbar sesuai instruksi di gambar tugas
                Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
            }
            show()
        }
    }
}