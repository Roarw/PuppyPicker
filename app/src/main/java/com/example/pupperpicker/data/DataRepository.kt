package com.example.pupperpicker.data

interface DataRepository {
    suspend fun getRandomDog(): Response?
}