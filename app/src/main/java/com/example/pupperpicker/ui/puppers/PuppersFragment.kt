package com.example.pupperpicker.ui.puppers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pupperpicker.databinding.FragmentPuppersBinding

class PuppersFragment : Fragment() {

    private var _binding: FragmentPuppersBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val puppersViewModel =
            ViewModelProvider(this).get(PuppersViewModel::class.java)

        _binding = FragmentPuppersBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textPuppers
        puppersViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}