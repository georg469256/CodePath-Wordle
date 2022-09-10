package com.example.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val wordToGuess = FourLetterWordList.getRandomFourLetterWord()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        val guess = findViewById<EditText>(R.id.userGuess)

        val guessButton = findViewById<Button>(R.id.guessButton)

        guess.filters += InputFilter.AllCaps()

        guess.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(input: CharSequence?, start: Int, before: Int, count: Int) {
                guessButton.isEnabled = (count == 4)
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        guessButton.setOnClickListener {
            val guessResult = checkGuess(guess.text.toString())
            Toast.makeText(this,"Word: $wordToGuess Guess: $guessResult", Toast.LENGTH_LONG).show()
        }

    }

}