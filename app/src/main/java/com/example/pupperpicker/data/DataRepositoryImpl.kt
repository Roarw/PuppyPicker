package com.example.pupperpicker.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepositoryImpl @Inject constructor() : DataRepository {
    override fun getRandomDog(): Response {
        return Response("https://images.dog.ceo/breeds/akita/Japaneseakita.jpg", "success")
    }
}