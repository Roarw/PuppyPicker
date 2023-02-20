package com.example.pupperpicker.data

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
            Log.w("DataFetch", response)
            return Json.decodeFromString<Response>(response)
        } catch (e: Exception) {
            Log.w("DataFetch", e.toString())
            return null
        }
    }
}