package com.aakash.fithub

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.aakash.fithub.`object`.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
@LargeTest
@RunWith(JUnit4 :: class)
class LoginTestUI {
    @get : Rule
    val testRule = ActivityScenarioRule(LoginActivity::class.java)
    @Test
    fun checkArithmeticUI() {
        Espresso.onView(withId(R.id.etUserName))
            .perform(ViewActions.typeText("d@gmail.com"))
        Thread.sleep(2000)
        Espresso.closeSoftKeyboard()

        Espresso.onView(withId(R.id.etPassword))
            .perform(ViewActions.typeText("12345"))
        Thread.sleep(2000)
        Espresso.closeSoftKeyboard()
        Espresso.onView(withId(R.id.btnLogin))
            .perform(ViewActions.click())
        
    }
}