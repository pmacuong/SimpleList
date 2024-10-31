package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etInput: EditText
    private lateinit var rbEven: RadioButton
    private lateinit var rbOdd: RadioButton
    private lateinit var rbSquare: RadioButton
    private lateinit var btnShow: Button
    private lateinit var lvNumbers: ListView
    private lateinit var tvError: TextView
    private lateinit var rgOptions: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etInput = findViewById(R.id.etInput)
        rbEven = findViewById(R.id.rbEven)
        rbOdd = findViewById(R.id.rbOdd)
        rbSquare = findViewById(R.id.rbSquare)
        btnShow = findViewById(R.id.btnShow)
        lvNumbers = findViewById(R.id.lvNumbers)
        tvError = findViewById(R.id.tvError)
        rgOptions = findViewById(R.id.rgOptions)

        btnShow.setBackgroundColor(resources.getColor(R.color.red))

        btnShow.setOnClickListener {
            val input = etInput.text.toString()
            if (input.isEmpty()) {
                tvError.text = "Please enter a positive integer"
                return@setOnClickListener
            }

            val n = input.toInt()
            if (n <= 0) {
                tvError.text = "Please enter a positive integer"
                return@setOnClickListener
            }

            val numbers = when (rgOptions.checkedRadioButtonId) {
                R.id.rbEven -> getEvenNumbers(n)
                R.id.rbOdd -> getOddNumbers(n)
                R.id.rbSquare -> getSquareNumbers(n)
                else -> emptyList()
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
            lvNumbers.adapter = adapter
            tvError.text = ""
        }
    }

    private fun getEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun getOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2!= 0 }
    }

    private fun getSquareNumbers(n: Int): List<Int> {
        return (0..n).filter { Math.sqrt(it.toDouble()).toInt() * Math.sqrt(it.toDouble()).toInt() == it }
    }
}