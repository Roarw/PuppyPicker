package com.example.pupperpicker

import android.app.Application
import com.example.pupperpicker.data.DBHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PuppyPickerApplication : Application() {

    var dbHelper: DBHelper? = null

    override fun onCreate() {
        super.onCreate()
        dbHelper = DBHelper(this, null)
    }
}