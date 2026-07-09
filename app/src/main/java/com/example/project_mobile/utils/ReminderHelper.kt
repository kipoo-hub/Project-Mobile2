package com.example.project_mobile.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import java.util.Calendar

object ReminderHelper {

    fun setReminder(
        context: Context,
        reminderId: Int, // Menambahkan ID unik
        hour: Int,
        minute: Int,
        title: String,
        message: String,
        targetActivity: Class<*>
    ) {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)

            if (before(Calendar.getInstance())) {
                add(Calendar.DAY_OF_MONTH, 1)
            }
        }

        // Setiap reminder didefinisikan judul, pesan dan Activity dituju saat diklik
        val intent = Intent(context, ReminderReceiver::class.java).apply {
            putExtra("title", title)
            putExtra("message", message)
            putExtra("target_activity", targetActivity.name)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            reminderId, // Menggunakan ID unik agar tidak saling menimpa
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (!alarmManager.canScheduleExactAlarms()) {
                val intentSettings = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                context.startActivity(intentSettings)
                Toast.makeText(context, "Silahkan berikan izin Alarm Presisi", Toast.LENGTH_LONG).show()
                return
            }
        }

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    }
}