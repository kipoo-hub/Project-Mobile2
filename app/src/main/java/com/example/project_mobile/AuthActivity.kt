package com.example.project_mobile // Pastikan sesuai dengan package name proyek Anda

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.project_mobile.databinding.ActivityAuthBinding

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

        // 3. Kondisi jika isLogin bernilai true, langsung ke MainActivity
        if (sharedPref.getBoolean("isLogin", false)) {
            pindahKeMain()
        }

        // Logika tombol login sesuai instruksi
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            // 4. Cek kondisi Username = Password
            if (username == password && username.isNotEmpty()) {

                // 5. Set isLogin menjadi true dan simpan username
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.apply()

                pindahKeMain()
            } else {
                // Tampilkan AlertDialog jika tidak sesuai
                showErrorDialog()
            }
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
            setMessage("Silahkan coba lagi") // Pesan sesuai instruksi image_2add3c.png
            setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            show()
        }
    }
}