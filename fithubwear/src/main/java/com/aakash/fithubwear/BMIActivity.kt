package com.aakash.fithubwear

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.aakash.fithubwear.bmi.BMIFormula

class BMIActivity : AppCompatActivity() {

    private lateinit var weight: EditText
    private lateinit var height: EditText
    private lateinit var btnCalculate: Button
    private lateinit var bmiResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_m_i)

        weight=findViewById(R.id.etWeight)
        height= findViewById(R.id.etHeight)
        btnCalculate= findViewById(R.id.btnCalculate)
        bmiResult=findViewById(R.id.bmiResult)

        btnCalculate.setOnClickListener{
            val h=height.text.toString()
            val w= weight.text.toString()
            val calc= BMIFormula()
            calc.height=h.toFloat()
            calc.weight=w.toFloat()
            val bmi=calc.bmi()

            bmiResult.setText("Your BMI Index is $bmi")



        }
    }
}