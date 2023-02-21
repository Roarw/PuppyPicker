package com.example.pupperpicker.ui.puppers

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pupperpicker.data.DBHelper
import com.example.pupperpicker.data.DataRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PuppersViewModel @Inject constructor (
    private val dataRepositoryImpl: DataRepositoryImpl
) : ViewModel() {

    var favoriteDogs: MutableLiveData<List<Pair<Bitmap?, String>>?> = MutableLiveData() // TODO: Handle state between views
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    fun loadFavoriteDogs(dbHelper: DBHelper?) {
        if (dbHelper != null) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    loading.postValue(true)

                    // Load favorite dogs
                    val urls = dbHelper.getAllURLs()

                    val pairs = ArrayList<Pair<Bitmap?, String>>(urls.size)
                    for (url in urls) {
                        // Load dog images
                        val bitmap: Bitmap? = dataRepositoryImpl.getDogImage(url)
                        pairs.add(Pair(bitmap, url))
                    }
                    favoriteDogs.postValue(pairs)

                    loading.postValue(false)
                }
            }
        }
    }
}