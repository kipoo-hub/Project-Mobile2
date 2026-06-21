package com.example.project_mobile.Message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project_mobile.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {
    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu, inflater: android.view.MenuInflater) {
        inflater.inflate(com.example.project_mobile.R.menu.menu_message, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return when (item.itemId) {
            com.example.project_mobile.R.id.action_tutorial -> {
                startActivity(android.content.Intent(requireContext(), com.example.project_mobile.tutorial.TutorialMessageActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 5. Definisi list data message
        val listMessage = listOf(
            MessageModel("https://i.pravatar.cc/150?u=alya", "Alya", "Halo! Apa kabar?"),
            MessageModel("https://i.pravatar.cc/150?u=budi", "Budi", "Sudah makan?"),
            MessageModel("https://i.pravatar.cc/150?u=citra", "Citra", "Jangan lupa tugasnya ya!"),
            MessageModel("https://i.pravatar.cc/150?u=dika", "Dika", "Besok kita rapat jam 9"),
            MessageModel("https://i.pravatar.cc/150?u=eka", "Eka", "Nice job kemarin!"),
            MessageModel("https://i.pravatar.cc/150?u=fajar", "Fajar", "Otw lokasi ya.")
        )

        // 6 & 7. Buat & Terapkan MessageAdapter
        val adapter = MessageAdapter(requireContext(), listMessage)
        binding.lvMessage.adapter = adapter

        // 8. Terapkan OnClick pada setiap item
        binding.lvMessage.setOnItemClickListener { _, _, position, _ ->
            val selectedMessage = listMessage[position]
            android.widget.Toast.makeText(
                requireContext(),
                "Menghubungi ${selectedMessage.name}...",
                android.widget.Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}