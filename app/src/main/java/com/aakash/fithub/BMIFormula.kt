package com.aakash.fithub

class BMIFormula {
    var height: Float=0F
    var weight: Float=0F

    fun bmi():Float{
        return (weight / height)
    }
}