package com.example.project_mobile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project_mobile.databinding.ActivityRegistrationBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = getSharedPreferences("UserSession", Context.MODE_PRIVATE)

        // Ambil email dari intent dan kunci (disable) agar tidak bisa diubah sesuai soal
        val email = intent.getStringExtra("EXTRA_EMAIL")
        binding.etEmail.setText(email)
        binding.etEmail.isEnabled = false

        binding.btnRegister.setOnClickListener {
            val nama = binding.etNama.text.toString()
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            // 1. Jalankan Validasi (Sesuai Soal b2)
            if (nama.isEmpty() || username.isEmpty() || password.isEmpty()) {
                showError("Semua field wajib diisi")
            } else if (password.length < 6) {
                showError("Password minimal 6 karakter")
            } else if (username.contains(" ")) {
                showError("Username tidak boleh mengandung spasi")
            } else {
                // 2. Simpan ke SharedPreferences
                val editor = sharedPref.edit()
                editor.putString("saved_nama", nama)
                editor.putString("saved_username", username)
                editor.putString("saved_password", password)
                editor.apply()

                // 3. Tampilkan Dialog Sukses & Arahkan Kembali ke Login
                com.google.android.material.dialog.MaterialAlertDialogBuilder(this)
                    .setTitle("Berhasil")
                    .setMessage("Registrasi Berhasil! Silahkan Login.")
                    .setPositiveButton("OK") { _, _ ->
                        // KUNCI PERBAIKAN: Gunakan Flags untuk membersihkan history
                        val intent = Intent(this, AuthActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                        startActivity(intent)
                        finish() // Menutup RegistrationActivity
                    }
                    .show()
            }
        }
    }

    private fun showError(message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Registrasi Gagal")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }

    private fun showSuccessDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Berhasil")
            .setMessage("Registrasi Berhasil")
            .setPositiveButton("OK") { _, _ ->
                // Kembali ke AuthActivity untuk Login
                finish()
            }
            .show()
    }
}