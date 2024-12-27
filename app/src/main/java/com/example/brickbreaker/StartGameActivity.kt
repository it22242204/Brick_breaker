package com.example.brickbreaker

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StartGameActivity : AppCompatActivity() {

    private lateinit var scoreText: TextView
    private lateinit var highscoreText: TextView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_game)

        scoreText = findViewById(R.id.scoreText)
        highscoreText = findViewById(R.id.highscoreText)
        val newgame: Button = findViewById(R.id.newgame)

        sharedPreferences = getSharedPreferences("high_score", Context.MODE_PRIVATE)
        val highScore = sharedPreferences.getInt("high_score", 0)
        highscoreText.text = "High Score: $highScore"

        // Retrieve the score from the intent extras
        val score = intent.getIntExtra("score", 0)

        // Display the score
        scoreText.text = "Score: $score"

        if (score > highScore) {
            // Update the high score if the current score is higher
            with(sharedPreferences.edit()) {
                putInt("high_score", score)
                apply()
            }
            highscoreText.text = "High Score: $score" // Update high score display
        }

        newgame.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
