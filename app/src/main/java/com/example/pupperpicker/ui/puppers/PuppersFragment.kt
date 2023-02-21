package com.example.pupperpicker.ui.puppers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.pupperpicker.PuppyPickerApplication
import com.example.pupperpicker.databinding.FragmentPuppersBinding

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
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        puppersViewModel.dogURLs.observe(viewLifecycleOwner) { dogURLs ->
            if (dogURLs != null)
            {
                Log.d("DataFun", dogURLs.toString())
            }
        }

        puppersViewModel.loadFavoriteDogs((requireActivity().applicationContext as PuppyPickerApplication).dbHelper)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}