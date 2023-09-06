package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.R

class MainActivity : AppCompatActivity() {

    //deklarasi variabel
    private lateinit var txtInput: TextView
    private lateinit var txtHasil: TextView

    private var inputText = ""
    private var operator = ""
    private var angkaPertama = 0.0
    private var angkaKedua = 0.0
    private var isOperatorSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtInput = findViewById(R.id.txt_input)
        txtHasil = findViewById(R.id.txt_hasil)

        val buttons = arrayOf(
            findViewById<Button>(R.id.btn0), findViewById<Button>(R.id.btn1),
            findViewById<Button>(R.id.btn2), findViewById<Button>(R.id.btn3),
            findViewById<Button>(R.id.btn4), findViewById<Button>(R.id.btn5),
            findViewById<Button>(R.id.btn6), findViewById<Button>(R.id.btn7),
            findViewById<Button>(R.id.btn8), findViewById<Button>(R.id.btn9),
            findViewById<Button>(R.id.btn_add), findViewById<Button>(R.id.btn_subtract),
            findViewById<Button>(R.id.btn_multiply), findViewById<Button>(R.id.btn_divide),
            findViewById<Button>(R.id.btn_percent), findViewById<Button>(R.id.btn_coma)
        )

        //menetapkan OnClickListener untuk semua button yang terdapat dalam buttons
        for (button in buttons) {
            button.setOnClickListener { onButtonClick(button.text.toString()) }
        }

        //menetapkan OnClickListener untuk button "="
        findViewById<Button>(R.id.btn_equals).setOnClickListener { calculateResult() }

        //menetapkan OnClockListener untuk button clear
        findViewById<Button>(R.id.btn_clear).setOnClickListener { clearCalculator() }

        //menetapkan OnClickListener untuk button back
        findViewById<Button>(R.id.btn_back).setOnClickListener {
            if (!inputText.isEmpty()) {
                inputText = inputText.substring(0, inputText.length - 1)
                updateInputText()
            }
        }
    }

    // fungsi ini dipanggil ketika tombol angka atau operator ditekan
    private fun onButtonClick(buttonText: String) {
        when (buttonText) {
            in "0123456789," -> {
                inputText += buttonText
                updateInputText()
            }
            "+", "-", "x", "/" -> {
                if (!isOperatorSelected) {
                    operator = buttonText
                    angkaPertama = inputText.toDouble()
                    inputText = operator + ""
                    updateInputText()
                    isOperatorSelected = true
                }
            }
        }
    }

    //fungsi ini digunakan untuk menghitung hasil perhitungan dan menampilkannya
    private fun calculateResult() {
        if (isOperatorSelected && !inputText.isEmpty()) {
            angkaKedua = inputText.toDouble()
            var result = 0.0

            when (operator) {
                "+" -> result = angkaPertama + angkaKedua
                "-" -> result = angkaPertama - angkaKedua
                "x" -> result = angkaPertama * angkaKedua
                "/" -> {
                    if (angkaKedua != 0.0) {
                        result = angkaPertama / angkaKedua
                    } else {
                        result = Double.POSITIVE_INFINITY
                    }
                }
            }

            txtHasil.text = result.toString()
            inputText = result.toString()
            isOperatorSelected = false
        }
    }

    //fungsi ini digunakan untuk menghapus semua data dan mengatur ulang kalkulator
    private fun clearCalculator() {
        inputText = ""
        operator = ""
        angkaPertama = 0.0
        angkaKedua = 0.0
        isOperatorSelected = false
        updateInputText()
        txtHasil.text = "Hasil"
    }

    //fungsi ini digunakan untuk memperbarui tampilan text input
    private fun updateInputText() {
        txtInput.text = inputText
    }
}



