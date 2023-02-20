package com.example.pupperpicker.ui.picker

import androidx.lifecycle.*
import com.example.pupperpicker.data.DataRepositoryImpl
import com.example.pupperpicker.data.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PickerViewModel @Inject constructor (
    private val dataRepositoryImpl: DataRepositoryImpl
) : ViewModel() {

    var dogResponse: MutableLiveData<Response?> = MutableLiveData()

    fun getDog() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = dataRepositoryImpl.getRandomDog()
                dogResponse.postValue(result)
            }
        }
    }
}