package com.example.pupperpicker.ui.picker

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.pupperpicker.R
import com.example.pupperpicker.databinding.FragmentPickerBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class PickerFragment : Fragment() {

    private val pickerViewModel: PickerViewModel by activityViewModels()
    private var dogSoundIDs: List<Int>? = null

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
            binding.removeThisText.text = dogResponse.message
        }
        pickerViewModel.getDog() // Force dog response to change

        setupSounds()

        binding.pupperButton.setOnClickListener {
            playDogSound()
        }
    }

    private fun setupSounds(){
        dogSoundIDs = listOf<Int>(
            R.raw.bark1,
            R.raw.bark2,
            R.raw.bark3,
            R.raw.bark4,
            R.raw.bark5,
            R.raw.bark6
        )
    }

    private fun playDogSound(){
        if (dogSoundIDs != null) {
            val randomIndex = Random.nextInt(dogSoundIDs!!.size)
            MediaPlayer.create(context, dogSoundIDs!![randomIndex]).start()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}