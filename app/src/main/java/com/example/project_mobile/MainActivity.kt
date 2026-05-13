package com.example.project_mobile // Sesuaikan dengan package name proyek Anda

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.project_mobile.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inisialisasi View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Inisialisasi SharedPreferences
        sharedPref = getSharedPreferences("UserSession", Context.MODE_PRIVATE)

        // Tampilkan username di UI jika diperlukan (Optional)
        val username = sharedPref.getString("username", "User")
        // binding.tvWelcome.text = "Selamat Datang, $username"

        // 3. Logika fitur Logout melalui btnLogout
        binding.btnLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    /**
     * Menampilkan AlertDialog konfirmasi dan menghapus data SharedPreferences
     */
    private fun showLogoutDialog() {
        AlertDialog.Builder(this).apply {
            setTitle("Konfirmasi Logout")
            setMessage("Apakah anda yakin ingin keluar?")

            // Jika memilih "Ya", hapus data session dan tutup activity
            setPositiveButton("Ya") { _, _ ->
                // Menghapus data SharedPreferences
                val editor = sharedPref.edit()
                editor.clear()
                editor.apply()

                // Berpindah kembali ke AuthActivity
                val intent = Intent(this@MainActivity, AuthActivity::class.java)
                startActivity(intent)

                // Menjalankan finish() sesuai instruksi
                finish()
            }

            // Jika memilih "Tidak", tutup dialog dan tampilkan feedback
            setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
                Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
            }
            show()
        }
    }
}