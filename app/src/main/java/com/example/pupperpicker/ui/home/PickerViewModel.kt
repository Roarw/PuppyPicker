package com.example.pupperpicker.ui.picker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PickerViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is picker Fragment"
    }
    val text: LiveData<String> = _text
}