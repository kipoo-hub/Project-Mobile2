package com.example.project_mobile // Pastikan sesuai dengan package name proyek Anda

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.project_mobile.databinding.ActivityAuthBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inisialisasi View Binding
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Definisi SharedPreferences dengan nama "UserSession"
        sharedPref = getSharedPreferences("UserSession", Context.MODE_PRIVATE)

        // 3. Cek Onboarding dulu sebelum Login
        val onboardingFinished = sharedPref.getBoolean("onboardingFinished", false)
        if (!onboardingFinished) {
            val intent = Intent(this, com.example.project_mobile.tutorial.TutorialMessageActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        // 4. Kondisi jika isLogin bernilai true, langsung ke MainActivity
        if (sharedPref.getBoolean("isLogin", false)) {
            pindahKeMain()
        }

        // Logika tombol login sesuai instruksi
        binding.btnLogin.setOnClickListener {
            val inputUsername = binding.etUsername.text.toString()
            val inputPassword = binding.etPassword.text.toString()

            // Ambil data yang tersimpan dari SharedPreferences
            val savedUsername = sharedPref.getString("saved_username", null)
            val savedPassword = sharedPref.getString("saved_password", null)

            // Kondisi sesuai soal b3:
            // 1. username == password
            // 2. ATAU sesuai dengan data yang tersimpan di SharedPreferences
            val isLoginDefault = inputUsername == inputPassword && inputUsername.isNotEmpty()
            val isLoginStored = inputUsername == savedUsername && inputPassword == savedPassword

            if (isLoginDefault || isLoginStored) {
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", inputUsername)
                editor.apply()

                pindahKeMain()
            } else {
                // Tampilkan error menggunakan MaterialAlertDialog sesuai soal
                MaterialAlertDialogBuilder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Username atau Password salah")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }

        // Fitur Register With Gmail: Navigasi ke halaman input email
        binding.btnRegisterGmail.setOnClickListener {
            val intent = Intent(this, InputEmailActivity::class.java)
            startActivity(intent)
        }
    }

    private fun pindahKeMain() {
        val intent = Intent(this, BaseActivity::class.java)
        startActivity(intent)
        finish() // Mengakhiri activity agar tidak bisa kembali ke halaman login
    }

    private fun showErrorDialog() {
        AlertDialog.Builder(this).apply {
            setTitle("Login Gagal")
            setMessage("Silahkan coba lagi")
            setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            show()
        }
    }
}
