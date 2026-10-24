package com.example.vangtichai

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * VangtiChai - CSE 489 Assignment 1.
 *
 * Enter a money amount with the on-screen numeric keypad (digits are appended
 * from the right) and the app shows how the amount breaks down into Taka
 * notes of 500, 100, 50, 20, 10, 5, 2 and 1.
 *
 * The same activity serves all four layout variants (phone/tablet x
 * portrait/landscape) through resource qualifiers; every variant uses the
 * same view ids, so no per-layout code is needed. The entered amount is kept
 * across configuration changes (e.g. rotation) via onSaveInstanceState.
 */
class MainActivity : AppCompatActivity() {

    companion object {
        // Denominations in descending order, as required by the assignment.
        private val NOTES = intArrayOf(500, 100, 50, 20, 10, 5, 2, 1)
        private const val KEY_AMOUNT = "amount"
        private const val MAX_DIGITS = 9
    }

    // The amount as a digit string: typing 2, 3, 4 yields "2", "23", "234".
    private var amount = ""

    private lateinit var tvAmount: TextView
    private val noteViews = mutableMapOf<Int, TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvAmount = findViewById(R.id.tvAmount)
        noteViews[500] = findViewById(R.id.tvNote500)
        noteViews[100] = findViewById(R.id.tvNote100)
        noteViews[50] = findViewById(R.id.tvNote50)
        noteViews[20] = findViewById(R.id.tvNote20)
        noteViews[10] = findViewById(R.id.tvNote10)
        noteViews[5] = findViewById(R.id.tvNote5)
        noteViews[2] = findViewById(R.id.tvNote2)
        noteViews[1] = findViewById(R.id.tvNote1)

        val digitIds = mapOf(
            R.id.key0 to "0",
            R.id.key1 to "1",
            R.id.key2 to "2",
            R.id.key3 to "3",
            R.id.key4 to "4",
            R.id.key5 to "5",
            R.id.key6 to "6",
            R.id.key7 to "7",
            R.id.key8 to "8",
            R.id.key9 to "9"
        )
        for ((id, digit) in digitIds) {
            findViewById<Button>(id).setOnClickListener { onDigit(digit) }
        }
        findViewById<Button>(R.id.keyClear).setOnClickListener { onClear() }

        // Restore the state saved before the last configuration change.
        amount = savedInstanceState?.getString(KEY_AMOUNT) ?: ""
        updateUi()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(KEY_AMOUNT, amount)
        super.onSaveInstanceState(outState)
    }

    private fun onDigit(digit: String) {
        if (amount.length < MAX_DIGITS) {
            amount += digit
            updateUi()
        }
    }

    private fun onClear() {
        amount = ""
        updateUi()
    }

    /** Refreshes the amount display and the per-note change counts. */
    private fun updateUi() {
        tvAmount.text = if (amount.isEmpty()) {
            getString(R.string.taka_label)
        } else {
            getString(R.string.taka_with_amount, amount)
        }

        var remaining = amount.toIntOrNull() ?: 0
        for (note in NOTES) {
            val count = remaining / note
            remaining %= note
            noteViews[note]?.text = getString(R.string.note_line, note, count)
        }
    }
}
