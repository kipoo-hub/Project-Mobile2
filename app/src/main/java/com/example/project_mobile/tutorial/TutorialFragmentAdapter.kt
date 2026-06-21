package com.example.project_mobile.tutorial

import android.graphics.Color
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TutorialFragmentAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnboardingFragment.newInstance(
                "Selamat Datang di Fitur Pesan!",
                "Dengan fitur ini, kamu bisa melihat daftar pesan masuk dari pengguna lain. Semua pesan ditampilkan lengkap dengan nama pengirim, avatar, dan isi pesan",
                Color.parseColor("#3B82F6") // Blue
            )
            1 -> OnboardingFragment.newInstance(
                "Tap untuk Melihat Detail!",
                "Ketuk salah satu pesan untuk melihat detailnya. Kamu juga bisa menambahkan aksi lain seperti membalas, menyimpan, atau membagikan pesan tersebut",
                Color.parseColor("#10B981") // Green
            )
            else -> OnboardingFragment.newInstance(
                "Mulai Gunakan Sekarang!",
                "Jelajahi fitur pesan dan temukan informasi penting dari komunitas pengguna. Desain dirancang simpel, cepat, dan mudah digunakan",
                Color.parseColor("#06B6D4"), // Cyan
                true
            )
        }
    }
}