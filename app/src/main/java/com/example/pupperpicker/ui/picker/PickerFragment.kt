package com.example.pupperpicker.ui.picker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.pupperpicker.databinding.FragmentPickerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PickerFragment : Fragment() {

    private val pickerViewModel: PickerViewModel by activityViewModels()

    private var _binding: FragmentPickerBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPickerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pickerViewModel.dogResponse.observe(viewLifecycleOwner) { dogResponse ->
            binding.textPicker.text = dogResponse.message
        }
        pickerViewModel.getDog() // Force dog response to change
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}