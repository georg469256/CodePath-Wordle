package com.example.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private val wordToGuess = FourLetterWordList.getRandomFourLetterWord()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val guess = findViewById<EditText>(R.id.userGuess)
        guess.filters += InputFilter.AllCaps()

        /**
         * Parameters / Fields:
         *   wordToGuess : String - the target word the user is trying to guess
         *   guess : String - what the user entered as their guess
         *
         * Returns a String of 'O', '+', and 'X', where:
         *   'O' represents the right letter in the right place
         *   '+' represents the right letter in the wrong place
         *   'X' represents a letter not in the target word
         */
        fun checkGuess(guess: String) : String {
            var result = ""
            for (i in 0..3) {
                if (guess[i] == wordToGuess[i]) {
                    result += "O"
                }
                else if (guess[i] in wordToGuess) {
                    result += "+"
                }
                else {
                    result += "X"
                }
            }
            return result
        }

    }

}