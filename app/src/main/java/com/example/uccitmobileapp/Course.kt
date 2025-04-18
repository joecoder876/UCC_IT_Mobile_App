package com.example.uccitmobileapp

data class Course(
    val code: String,
    val name: String,
    val credits: Int,
    val prerequisites: String,
    val description: String
)