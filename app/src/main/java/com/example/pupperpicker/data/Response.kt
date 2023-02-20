package com.example.pupperpicker.data

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val message:String,
    val status:String
)
