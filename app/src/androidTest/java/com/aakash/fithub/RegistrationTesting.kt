package com.aakash.fithub

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.aakash.fithub.ui.RegisterActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4 :: class)
class RegistrationTesting {

    @get : Rule
    val testRule = ActivityScenarioRule(RegisterActivity::class.java)
    @Test

    fun RegistrationUITetging() {
        Espresso.onView(ViewMatchers.withId(R.id.etfname))
                .perform(ViewActions.typeText("Test"))
        Thread.sleep(2000)
        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.etlname))
                .perform(ViewActions.typeText("Test"))
        Thread.sleep(2000)
        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.etEmail))
                .perform(ViewActions.typeText("Test123@gmail.com"))
        Thread.sleep(2000)
        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.etPassword))
                .perform(ViewActions.typeText("test12"))
        Thread.sleep(2000)
        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.etCPassword))
                .perform(ViewActions.typeText("test12"))
        Thread.sleep(2000)
        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.btnRegister))
                .perform(ViewActions.click())


        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.register))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))



    }


}