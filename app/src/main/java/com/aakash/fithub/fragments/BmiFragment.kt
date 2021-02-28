package com.aakash.fithub.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.aakash.fithub.BMIFormula
import com.aakash.fithub.R



class BmiFragment : Fragment() {

    private lateinit var weight: EditText
    private lateinit var height: EditText
    private lateinit var btnCalculate: Button



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_bmi, container, false)

        val view: View = inflater.inflate(R.layout.fragment_bmi, container, false)

        weight=view.findViewById(R.id.etWeight)
        height= view.findViewById(R.id.etHeight)
        btnCalculate= view.findViewById(R.id.btnCalculate)

        btnCalculate.setOnClickListener{
            val h=height.text.toString()
            val w= weight.text.toString()
            val calc= BMIFormula()
            calc.height=h.toDouble()
            calc.weight=w.toDouble()
            val bmi=calc.bmi()

            //Toast.makeText(this,"$bmi", Toast.LENGTH_SHORT).show()
        }
        return view


    }




}