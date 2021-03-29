package com.aakash.fithub.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aakash.fithub.R
import com.aakash.fithub.api.ServiceBuilder
import com.aakash.fithub.entity.User
import com.aakash.fithub.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val sharedPref = getSharedPreferences("MyPref", AppCompatActivity.MODE_PRIVATE)
        val emailPref = sharedPref.getString("email", "")
        val passwordPref = sharedPref.getString("password", "")

        CoroutineScope(Dispatchers.Main).launch {
            delay(1500)
            val repository = UserRepository()
            val user = User(email = emailPref, password = passwordPref)
            val response = repository.checkUser(user)

            if (response.success == true) {
                ServiceBuilder.token="Bearer ${response.token}"
                ServiceBuilder.id=response.id

                startActivity(Intent(this@SplashActivity, MainActivity::class.java)
                )
                finish()
            }
            else{
                startActivity(Intent(this@SplashActivity, SliderActivity::class.java)
                )
                finish()

            }
        }
    }
}