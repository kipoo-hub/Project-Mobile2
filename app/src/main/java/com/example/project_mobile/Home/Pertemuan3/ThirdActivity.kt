// 1. Sesuaikan package dengan folder tempat file ini berada (Pertemuan3)
package com.example.project_mobile.Home.Pertemuan3

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project_mobile.Home.Pertemuan4.DashboardActivity
import com.example.project_mobile.databinding.ActivityThirdBinding
import com.example.project_mobile.utils.PermissionHelper
import com.example.project_mobile.utils.ReminderHelper
import java.util.Calendar

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Notifikasi diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifikasi ditolak", Toast.LENGTH_SHORT).show()
            }
        }

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

        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(
                    notificationPermissionLauncher,
                    permission
                )
            }
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                // 1. Alur Login: Pindah ke Dashboard
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)

                // 2. Alur Reminder: Setel pengingat 1 menit
                val calendar = Calendar.getInstance().apply {
                    add(Calendar.MINUTE, 1)
                }

                ReminderHelper.setReminder(
                    context = this,
                    reminderId = System.currentTimeMillis().toInt(), // ID unik
                    hour = calendar.get(Calendar.HOUR_OF_DAY),
                    minute = calendar.get(Calendar.MINUTE),
                    title = "Reminder Login",
                    message = "Halo $username, Anda berhasil login 1 menit yang lalu.",
                    targetActivity = ThirdResultActivity::class.java
                )

                Toast.makeText(this, "Login Berhasil! Reminder disetel 1 menit.", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                if (username.isEmpty()) binding.etUsername.error = "Masukkan Username/Email"
                if (password.isEmpty()) binding.etPassword.error = "Masukkan Password"

                Toast.makeText(this, "Harap isi semua kolom!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}