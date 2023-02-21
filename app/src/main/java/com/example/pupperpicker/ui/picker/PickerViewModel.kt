package com.example.pupperpicker.ui.picker

import android.graphics.Bitmap
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
    private var dogResponse: Response? = null // TODO: Handle state between views

    var dogImage: MutableLiveData<Bitmap?> = MutableLiveData()
    var dogURL: MutableLiveData<String> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    fun loadNextDog() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                loading.postValue(true)

                // Load next dog
                dogResponse = dataRepositoryImpl.getRandomDog()

                if (dogResponse != null) {
                    // Load dog image
                    val bitmap: Bitmap? = dataRepositoryImpl.getDogImage(dogResponse!!)
                    dogImage.postValue(bitmap)
                    dogURL.postValue(dogResponse!!.message)
                }

                loading.postValue(false)
            }
        }
    }
}