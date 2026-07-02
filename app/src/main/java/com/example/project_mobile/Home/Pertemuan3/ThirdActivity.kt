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
import com.example.project_mobile.databinding.ActivityThirdBinding
import com.example.project_mobile.Home.Pertemuan4.DashboardActivity
import com.example.project_mobile.utils.NotificationHelper
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
                val intent = Intent(this, DashboardActivity::class.java)
                // startActivity(intent)

                // NotificationHelper.showNotification(
                //    this,
                //    "Pesanan Anda",
                //    "Halo $username, Pesanan Anda Sedang Diproses",
                //    intent
                // )

                val calendar = Calendar.getInstance().apply {
                    add(Calendar.MINUTE, 1) // Tambah 1 menit dari sekarang
                }

                ReminderHelper.setReminder(
                    context = this,
                    hour = calendar.get(Calendar.HOUR_OF_DAY),
                    minute = calendar.get(Calendar.MINUTE),
                    title = "Reminder 1 Menit",
                    message = "Halo $username, reminder ini muncul 1 menit setelah Anda klik tombol",
                    targetActivity = ThirdResultActivity::class.java
                )

                Toast.makeText(this, "Silahkan tunggu 1 Menit untuk menerima Notifikasi", Toast.LENGTH_SHORT).show()
                // finish()
            } else {
                if (username.isEmpty()) binding.etUsername.error = "Masukkan Username/Email"
                if (password.isEmpty()) binding.etPassword.error = "Masukkan Password"

                Toast.makeText(this, "Harap isi semua kolom!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}