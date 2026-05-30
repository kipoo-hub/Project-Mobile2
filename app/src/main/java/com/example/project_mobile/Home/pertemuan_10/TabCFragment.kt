package com.example.project_mobile.Home.pertemuan_10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.project_mobile.databinding.FragmentTabCBinding

class TabCFragment : Fragment() {
    private var _binding: FragmentTabCBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listProduct = listOf(
            ProductModel("Sepatu Running Nike", "Rp 850.000", "https://images.unsplash.com/photo-1542291026-7eec264c27ff"),
            ProductModel("Kemeja Flanel", "Rp 320.000", "https://images.unsplash.com/photo-1589310243389-96a5483213a8"),
            ProductModel("Tas Ransel Laptop", "Rp 450.000", "https://images.unsplash.com/photo-1553062407-98eeb94c6a62"),
            ProductModel("Jam Tangan Casio", "Rp 1.200.000", "https://images.unsplash.com/photo-1524592094714-0f0654e20314"),
            ProductModel("Headphone Sony", "Rp 1.500.000", "https://images.unsplash.com/photo-1505740420928-5e560c06d30e"),
            ProductModel("Kacamata Premium", "Rp 150.000", "https://images.unsplash.com/photo-1572635196237-14b3f281503f")
        )

        val adapter = ProductAdapter(listProduct)
        binding.rvProduct.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvProduct.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}