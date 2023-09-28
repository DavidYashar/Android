package com.example.bmi

data class User(val age: Int, val gender: Gender, val weightKg: Double, val heightCm: Double)

enum class Gender {
    MALE, FEMALE, OTHER
}