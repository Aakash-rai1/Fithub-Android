package com.aakash.fithub

import org.junit.Assert
import org.junit.Test


private lateinit var arithmetic: BMIFormula

class BMICalculateTest {
    @Test
    fun testSum(){
        arithmetic = BMIFormula()
        arithmetic.height=2F
        arithmetic.weight= 50F

        val expectedResult =25F
        val actualResult= arithmetic.bmi()
        Assert.assertEquals(expectedResult,actualResult)
    }

}