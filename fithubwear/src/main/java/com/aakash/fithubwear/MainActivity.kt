package com.aakash.fithubwear

import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.widget.*
import com.aakash.fithubwear.Repository.UserRepository
import com.aakash.fithubwear.api.ServiceBuilder
import com.aakash.fithubwear.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : WearableActivity() {
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)


        btnLogin.setOnClickListener(){
            CoroutineScope(Dispatchers.IO).launch {
                val repository=UserRepository()
                val response=repository.checkUser(User(email = etEmail.text.toString(),password = etPassword.text.toString()))
                if(response.success==true){
                    ServiceBuilder.token="Bearer ${response.token}"
                    ServiceBuilder.id=response.id
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@MainActivity, "${response.id}", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@MainActivity,BMIActivity::class.java))
                    }
                }
            }

//            val intent= Intent(this, BMIActivity::class.java)
//            startActivity(intent)
        }




        // Enables Always-on
        setAmbientEnabled()
    }

//    private fun login() {
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val repository = UserRepository()
//                val response = repository.checkUser(User(email = etEmail.text.toString(), password = etPassword.text.toString()))
//
//                if (response.success == true) {
//                    ServiceBuilder.token = "Bearer " + response.token!!
//                    ServiceBuilder.id = response.id!!
//                    Toast.makeText(this@MainActivity, "sad", Toast.LENGTH_SHORT).show()
//
//                    startActivity(
//                        Intent(
//                            this@MainActivity,
//                            BMIActivity::class.java
//                        )
//                    )
//                    finish()
//                } else {
//                    withContext(Dispatchers.IO) {
//                    Toast.makeText(applicationContext, "sad", Toast.LENGTH_SHORT).show()
//
//                    }
//                }
//
//            } catch (ex: Exception) {
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(
//                        this@MainActivity,
//                        "Login error", Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        }
//    }
}