package com.aakash.fithub

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.aakash.fithub.ui.LoginActivity
import com.aakash.fithub.ui.RegisterActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@LargeTest
@RunWith(JUnit4 :: class)
class UserProfileTest {

    @get : Rule
    val testRule = ActivityScenarioRule(LoginActivity::class.java)
    @Test

    fun UserProfileUITesting() {
        Espresso.onView(ViewMatchers.withId(R.id.etUserName))
                .perform(ViewActions.typeText("Test123@gmail.com"))
        Thread.sleep(2000)
        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.etPassword))
                .perform(ViewActions.typeText("test12"))
        Thread.sleep(2000)
        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.btnLogin))
                .perform(ViewActions.click())


        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.fragmentContainer))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.icProfile))
                .perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.profilefragment))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}