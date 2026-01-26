package com.cp.quizapp.data

import com.cp.quizapp.data.Question

class QuestionRepository {

    fun getQuestions(): List<Question> {
        return listOf(
            Question(
                question = "What is the capital of India?",
                options = listOf("Mumbai", "New Delhi", "Kolkata", "Chennai"),
                correctAnswerIndex = 1
            ),

            Question(
                question = "Which is the largest planet in our solar system?",
                options = listOf("Earth", "Saturn", "Jupiter", "Mars"),
                correctAnswerIndex = 2
            ),

            Question(
                question = "Who is known as the Father of the Nation in India?",
                options = listOf("Jawaharlal Nehru", "Mahatma Gandhi", "B. R. Ambedkar", "Subhash Chandra Bose"),
                correctAnswerIndex = 1
            ),

            Question(
                question = "Which is the longest river in the world?",
                options = listOf("Amazon", "Ganga", "Yangtze", "Nile"),
                correctAnswerIndex = 3
            ),

            Question(
                question = "Which country is known as the Land of the Rising Sun?",
                options = listOf("China", "South Korea", "Japan", "Thailand"),
                correctAnswerIndex = 2
            ),

            Question(
                question = "How many continents are there in the world?",
                options = listOf("5", "6", "8", "7"),
                correctAnswerIndex = 3
            ),

            Question(
                question = "Which gas do plants absorb from the atmosphere?",
                options = listOf("Oxygen", "Nitrogen", "Carbon Dioxide", "Hydrogen"),
                correctAnswerIndex = 2
            ),

            Question(
                question = "Who wrote the national anthem of India?",
                options = listOf("Sarojini Naidu", "Bankim Chandra Chatterjee", "Rabindranath Tagore", "Subhash Chandra Bose"),
                correctAnswerIndex = 2
            ),
            Question(
                question = "Which ocean is the largest in the world?",
                options = listOf("Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"),
                correctAnswerIndex = 3
            ),

            Question(
                question = "Which planet is known as the Red Planet?",
                options = listOf("Jupiter", "Venus", "Mars", "Saturn"),
                correctAnswerIndex = 2
            )
        )
    }
}
