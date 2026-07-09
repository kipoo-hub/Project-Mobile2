package com.example.project_mobile.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.project_mobile.Home.Pertemuan4.DashboardActivity

class ReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val title = intent.getStringExtra("title") ?: "Pengingat"
        val message = intent.getStringExtra("message") ?: "Waktunya melakukan aktivitas!"
        val targetClassName = intent.getStringExtra("target_activity")

        val targetIntent = if (!targetClassName.isNullOrEmpty()) {
            try {
                val clazz = Class.forName(targetClassName)
                Intent(context, clazz).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
            } catch (_: ClassNotFoundException) {
                Intent(context, DashboardActivity::class.java)
            }
        } else {
            Intent(context, DashboardActivity::class.java)
        }

        NotificationHelper.showNotification(
            context = context,
            title = title,
            message = message,
            intent = targetIntent
        )
    }
}