package com.example.pupperpicker.ui.picker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pupperpicker.data.DataRepositoryImpl
import com.example.pupperpicker.data.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PickerViewModel @Inject constructor (
    private val dataRepositoryImpl: DataRepositoryImpl
) : ViewModel() {

    var dogResponse: MutableLiveData<Response> = MutableLiveData()

    // TODO: This is a coroutine! Remove this text
    fun getDog(): LiveData<Response> {
        viewModelScope.launch {
            val result = dataRepositoryImpl.getRandomDog()
            dogResponse.postValue(result)
        }
        return dogResponse
    }
}