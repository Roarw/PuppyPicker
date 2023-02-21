package com.example.pupperpicker.ui.puppers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pupperpicker.data.DBHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PuppersViewModel : ViewModel() {

    var dogURLs: MutableLiveData<List<String>?> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    fun loadFavoriteDogs(dbHelper: DBHelper?) {
        if (dbHelper != null) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    loading.postValue(true)

                    // Load favorite dogs
                    dogURLs.postValue(dbHelper.getAllURLs())

                    loading.postValue(false)
                }
            }
        }
    }
}