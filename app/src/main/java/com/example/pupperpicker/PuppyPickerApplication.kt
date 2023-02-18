package com.example.pupperpicker

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PuppyPickerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}