package com.cp.quizapp.ui.quiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.cp.quizapp.R
import com.cp.quizapp.data.Question
import com.cp.quizapp.data.QuestionRepository
import com.cp.quizapp.ui.result.ResultActivity

class QuizActivity : AppCompatActivity() {

    private lateinit var tvQuestion: TextView
    private lateinit var rgOptions: RadioGroup
    private lateinit var btnSubmit: Button

    private val repository = QuestionRepository()
    private lateinit var questions: List<Question>

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        tvQuestion = findViewById(R.id.tvQuestion)
        rgOptions = findViewById(R.id.rgOptions)
        btnSubmit = findViewById(R.id.btnSubmit)

        questions = repository.getQuestions()
        showQuestion()

        btnSubmit.setOnClickListener {
            checkAnswerAndMoveNext()
        }
    }

    private fun showQuestion() {
        val question = questions[currentQuestionIndex]

        tvQuestion.text = question.question

        for (i in 0 until rgOptions.childCount) {
            val radioButton = rgOptions.getChildAt(i) as RadioButton
            radioButton.text = question.options[i]
        }

        rgOptions.clearCheck()
    }

    private fun checkAnswerAndMoveNext() {
        val selectedId = rgOptions.checkedRadioButtonId
        if (selectedId == -1) return

        val selectedIndex = rgOptions.indexOfChild(findViewById(selectedId))
        val correctIndex = questions[currentQuestionIndex].correctAnswerIndex

        if (selectedIndex == correctIndex) {
            score++
        }

        currentQuestionIndex++

        if (currentQuestionIndex >= questions.size) {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("SCORE", score)
            intent.putExtra("TOTAL", questions.size)
            startActivity(intent)
            finish()
        }

    }
}
