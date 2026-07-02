package com.example.project_mobile.Home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_mobile.Home.Pertemuan2.RumusActivity
import com.example.project_mobile.Home.Pertemuan3.ThirdResultActivity
import com.example.project_mobile.Home.Pertemuan4.DashboardActivity
import com.example.project_mobile.Home.Pertemuan5.WebViewActivity
import com.example.project_mobile.R
import com.example.project_mobile.SplashScreenActivity
import com.example.project_mobile.databinding.FragmentHomeBinding
import com.example.project_mobile.data.api.ApiClient
import com.example.project_mobile.data.api.NewsAdapter
import com.example.project_mobile.data.model.NewsResponse
import com.example.project_mobile.data.model.QuoteResponse
import com.example.project_mobile.data.model.PhotoResponse
import com.example.project_mobile.Home.photo.PhotoAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigation()
        loadQuote()
        loadNews() // Memanggil fungsi berita
        loadPhotos()

        binding.btnRefresh.setOnClickListener {
            loadQuote()
        }
    }

    private fun loadNews() {
        binding.rvNews.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvNewsVertical.layoutManager = LinearLayoutManager(requireContext())

        ApiClient.apiService.getLatestNews().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (_binding == null) return

                if (response.isSuccessful) {
                    // Berhasil nyambung ke API
                    val posts = response.body()?.data ?: emptyList()
                    android.util.Log.d("CEK_BERITA", "Koneksi sukses! Jumlah data yang diterima: ${posts.size}")

                    if (posts.isNotEmpty()) {
                        binding.rvNews.adapter = NewsAdapter(posts.take(5), isVertical = false)
                        binding.rvNewsVertical.adapter = NewsAdapter(posts.drop(5), isVertical = true)
                    } else {
                        android.util.Log.e("CEK_BERITA", "Koneksi sukses, TAPI datanya kosong. Coba cek apakah struktur JSON API benar-benar memakai key 'data'.")
                    }
                } else {
                    // Nyambung ke server, tapi server menolak (Error 400/500)
                    android.util.Log.e("CEK_BERITA", "Error dari server: ${response.code()} - ${response.message()}")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                if (_binding == null) return
                // Gagal nyambung (Misal: tidak ada internet atau URL salah)
                android.util.Log.e("CEK_BERITA", "Gagal total memanggil API: ${t.message}")
            }
        })
    }
    private fun loadPhotos() {
        ApiClient.apiService.getPhotos().enqueue(object : Callback<List<PhotoResponse>> {
            override fun onResponse(call: Call<List<PhotoResponse>>, response: Response<List<PhotoResponse>>) {
                if (_binding == null) return
                if (response.isSuccessful) {
                    val photos = response.body() ?: emptyList()
                    binding.rvPhotos.adapter = PhotoAdapter(photos)
                }
            }
            override fun onFailure(call: Call<List<PhotoResponse>>, t: Throwable) {
                if (_binding == null) return
            }
        })
    }

    private fun loadQuote() {
        binding.tvQuote.text = "Mengambil kutipan..."
        ApiClient.apiService.getRandomQuote().enqueue(object : Callback<QuoteResponse> {
            override fun onResponse(call: Call<QuoteResponse>, response: Response<QuoteResponse>) {
                if (_binding == null) return
                if (response.isSuccessful) {
                    val quoteData = response.body()
                    binding.tvQuote.text = "\"${quoteData?.quote}\"\n\n— ${quoteData?.author}"
                }
            }
            override fun onFailure(call: Call<QuoteResponse>, t: Throwable) {
                if (_binding == null) return
                binding.tvQuote.text = "Error koneksi."
            }
        })
    }

    private fun setupNavigation() {
        binding.btnToSecond.setOnClickListener { startActivity(Intent(requireContext(), RumusActivity::class.java)) }
        binding.btnToThird.setOnClickListener { startActivity(Intent(requireContext(), ThirdResultActivity::class.java)) }
        binding.btnToFourth.setOnClickListener { startActivity(Intent(requireContext(), DashboardActivity::class.java)) }
        binding.btnToFifth.setOnClickListener { startActivity(Intent(requireContext(), WebViewActivity::class.java)) }
        binding.btnToSixth.setOnClickListener { startActivity(Intent(requireContext(), SplashScreenActivity::class.java)) }
        binding.btnToTenth.setOnClickListener {
            startActivity(Intent(requireContext(), com.example.project_mobile.Home.pertemuan_10.TenthActivity::class.java))
        }
        binding.btnToThirteenth.setOnClickListener {
            startActivity(Intent(requireContext(), com.example.project_mobile.Home.pertemuan_13.ThirteenthActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}