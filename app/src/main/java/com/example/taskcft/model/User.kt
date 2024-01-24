package com.example.taskcft.model

import java.util.Date

data class User (
    val firstName: String,
    val secondName: String,
    val dataOfBirth: Date,
    val password: String
)