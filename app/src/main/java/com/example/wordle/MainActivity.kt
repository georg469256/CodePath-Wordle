package com.example.wordle

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout


class MainActivity : AppCompatActivity() {
    private val wordToGuess = FourLetterWordList.getRandomFourLetterWord()
    private var guessNumber = 1
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

        /**
         * Parameters / Fields:
         *   N/A
         *
         * Returns nothing:
         *   closes soft keyboard
         */
        fun closeKeyboard() {
            val view = this.currentFocus
            if(view != null) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        }

        val guess = findViewById<EditText>(R.id.userGuess)

        val guessButton = findViewById<Button>(R.id.guessButton)

        val group1 = findViewById<ConstraintLayout>(R.id.group1)
        val guess1 = findViewById<TextView>(R.id.Guess1)
        val guess1Check = findViewById<TextView>(R.id.Guess1Check)

        val group2 = findViewById<ConstraintLayout>(R.id.group2)
        val guess2 = findViewById<TextView>(R.id.Guess2)
        val guess2Check = findViewById<TextView>(R.id.Guess2Check)

        val group3 = findViewById<ConstraintLayout>(R.id.group3)
        val guess3 = findViewById<TextView>(R.id.Guess3)
        val guess3Check = findViewById<TextView>(R.id.Guess3Check)

        val secretWord = findViewById<TextView>(R.id.SecretWord)

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

            if(guessNumber == 1) {
                guess1.text = guess.text
                guess1Check.text = guessResult
                group1.visibility = View.VISIBLE
            }

            else if(guessNumber == 2) {
                guess2.text = guess.text
                guess2Check.text = guessResult
                group2.visibility = View.VISIBLE
            }

            else if(guessNumber == 3) {
                guess3.text = guess.text
                guess3Check.text = guessResult
                group3.visibility = View.VISIBLE
            }

            if(guessResult == "OOOO" || guessNumber == 3) {
                secretWord.text = wordToGuess
                guessButton.isEnabled = false
                guess.isEnabled = false
            }

            guess.setText("")
            guessNumber++
            closeKeyboard()

        }

    }

}