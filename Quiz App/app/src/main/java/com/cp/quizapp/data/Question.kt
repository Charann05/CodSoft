package com.cp.quizapp.data

data class Question(
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)
