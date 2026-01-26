package com.cp.quizapp.ui.result

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.cp.quizapp.MainActivity
import com.cp.quizapp.R

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvScore = findViewById<TextView>(R.id.tvScore)
        val btnHome = findViewById<Button>(R.id.btnHome)

        val score = intent.getIntExtra("SCORE", 0)
        val total = 10

        tvScore.text = "Your Score: $score / $total"

        btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
