package com.example.pupperpicker.data

import android.graphics.Bitmap

interface DataRepository {
    suspend fun getRandomDog(): Response?
    suspend fun getDogImage(response: Response): Bitmap?
    suspend fun getDogImage(url: String): Bitmap?
}