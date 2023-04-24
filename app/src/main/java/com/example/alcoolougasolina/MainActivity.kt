package com.example.alcoolougasolina

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var percentual:Double = 0.7
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val switchPercentual = findViewById<Switch>(R.id.swPercentual)
        val sharedPrefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        val getPercentualMem = sharedPrefs.getFloat("percentual", 0.0F)
        if (getPercentualMem != 0.0F) {
            percentual = getPercentualMem.toDouble()
            switchPercentual.isChecked = true
        }
        Log.d("PDM23","No onCreate, $percentual")

        val btCalc: Button = findViewById(R.id.btCalcular)
        btCalc.setOnClickListener(View.OnClickListener {
            Log.d("PDM23","No btCalcular, $percentual")
            val inputAlcool = findViewById<EditText>(R.id.edAlcool).text.toString()
            val inputGasolina = findViewById<EditText>(R.id.edGasolina).text.toString()
            val resultText = findViewById<TextView>(R.id.resultText)
            var resultCalc = 0.0
            if (inputGasolina.isNotEmpty() && inputAlcool.isNotEmpty()) {
                resultCalc = inputAlcool.toDouble() / inputGasolina.toDouble()
            }

            if (resultCalc == 0.0) {
                if (inputAlcool.isEmpty()) {
                    resultText.setText("Digite o valor do álcool")
                } else {
                    resultText.setText("Digite o valor da gasolina")
                }
            } else {
                if (resultCalc < percentual) {
                    resultText.setText("É melhor utilizar o álcool")
                } else {
                    resultText.setText("É melhor utilizar a gasolina")
                }
            }
        })

        switchPercentual.setOnClickListener {
            if (switchPercentual.isChecked) {
                percentual = 0.75
                editor.putFloat("percentual", percentual.toFloat())
                editor.apply()
            } else {
                percentual = 0.70
                if (sharedPrefs.getFloat("percentual", 0.0F) != 0.0F) {
                    editor.remove("percentual")
                    editor.apply()
                }
            }
            Log.d("PDM23", "Percentual depois do switch: $percentual")
        }
    }
override fun onResume(){
    super.onResume()
    Log.d("PDM23","No onResume, $percentual")
}
override fun onStart(){
    super.onStart()
    Log.d("PDM23","No onResume")
}
override fun onPause(){
    super.onPause()
    Log.d("PDM23","No onResume")
}
override fun onStop(){
    super.onStop()
    Log.d("PDM23","No onResume")
}
override fun onDestroy(){
    super.onDestroy()
    Log.d("PDM23","No onResume")
}
}