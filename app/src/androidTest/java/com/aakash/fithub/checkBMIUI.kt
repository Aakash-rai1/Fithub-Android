package com.aakash.fithub

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.aakash.fithub.ui.MainActivity
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@LargeTest
@RunWith(JUnit4 :: class)

class checkBMIUI {
    @get : Rule
    val testRule = ActivityScenarioRule(MainActivity::class.java)
}