package com.example.pupperpicker.ui.puppers

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pupperpicker.PuppyPickerApplication
import com.example.pupperpicker.R
import com.example.pupperpicker.databinding.FragmentPuppersBinding
import com.example.pupperpicker.ui.custom.DogCardAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PuppersFragment : Fragment() {

    private var _binding: FragmentPuppersBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val puppersViewModel: PuppersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPuppersBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.favoritePuppers.layoutManager = layoutManager

        // Spacing between dogs
        val dividerItemDecoration = DividerItemDecoration(
            binding.favoritePuppers.context,
            layoutManager.orientation
        )
        val spacing = ContextCompat.getDrawable(binding.favoritePuppers.context, R.drawable.ic_spacing)
        if (spacing != null) {
            dividerItemDecoration.setDrawable(spacing)
        }
        binding.favoritePuppers.addItemDecoration(dividerItemDecoration)

        puppersViewModel.favoriteDogs.observe(viewLifecycleOwner) { favoriteDogs ->
            if (favoriteDogs != null)
            {
                val adapter = DogCardAdapter(viewLifecycleOwner, puppersViewModel.viewModelScope, favoriteDogs)
                binding.favoritePuppers.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }

        puppersViewModel.loadFavoriteDogs((requireActivity().applicationContext as PuppyPickerApplication).dbHelper)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}