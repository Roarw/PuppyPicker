package com.example.pupperpicker.ui.puppers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PuppersViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is puppers Fragment"
    }
    val text: LiveData<String> = _text
}