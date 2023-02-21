package com.example.pupperpicker.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepositoryImpl @Inject constructor() : DataRepository {
    override suspend fun getRandomDog(): Response? {
        try {
            val response = URL("https://dog.ceo/api/breeds/image/random").readText()
            return Json.decodeFromString<Response>(response)
        } catch (e: Exception) {
            Log.w("DataFetch", e.toString())
            return null
        }
    }

    override suspend fun getDogImage(response: Response): Bitmap? {
        try {
            val url = URL(response.message)
            return BitmapFactory.decodeStream(url.openConnection().getInputStream())
        } catch (e: Exception) {
            Log.w("DataFetch", e.toString())
            return null
        }
    }

    override suspend fun getDogImage(url: String): Bitmap? {
        try {
            val url = URL(url)
            return BitmapFactory.decodeStream(url.openConnection().getInputStream())
        } catch (e: Exception) {
            Log.w("DataFetch", e.toString())
            return null
        }
    }
}