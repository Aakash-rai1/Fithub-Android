package com.aakash.fithub.ui

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.aakash.fithub.R
import com.aakash.fithub.api.ServiceBuilder
import com.aakash.fithub.entity.User
import com.aakash.fithub.repository.UserRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private val permissions = arrayOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.ACCESS_FINE_LOCATION
    )


    private lateinit var etUserName: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var LinearLayout: LinearLayout
    private lateinit var signup: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        etUserName = findViewById(R.id.etUserName)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        signup = findViewById(R.id.signup)
        LinearLayout = findViewById(R.id.layout)


        checkRunTimePermission()

        btnLogin.setOnClickListener {
            login()
        }
        signup.setOnClickListener(this)


    }

    private fun checkRunTimePermission() {
        if (!hasPermission()) {
            requestPermission()
        }
    }

    private fun hasPermission(): Boolean {
        var hasPermission = true
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(
                            this,
                            permission
                    ) != PackageManager.PERMISSION_GRANTED
            ) {
                hasPermission = false
                break
            }
        }
        return hasPermission
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this@LoginActivity, permissions, 1)
    }


    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.signup -> {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)

            }


        }
    }

    private fun login() {
        saveData()
        var user = User(email = etUserName.text.toString(), password = etPassword.text.toString())
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = UserRepository()
                val response = repository.checkUser(user = user)
                if (response.success == true) {
                    saveSharedPref(email = etUserName.text.toString(), password = etPassword.text.toString())
                    ServiceBuilder.token = "Bearer " + response.token!!
                    ServiceBuilder.id = response.id!!
                    startActivity(
                            Intent(
                                    this@LoginActivity,
                                    MainActivity::class.java
                            )
                    )
                    finish()
                } else {
                    withContext(Dispatchers.IO) {

                        val snackBar = Snackbar.make(
                                LinearLayout, "Replace with your own action",
                                Snackbar.LENGTH_LONG
                        ).setAction("Action", null)
                        snackBar.setActionTextColor(Color.BLUE)
                        val snackBarView = snackBar.view
                        snackBarView.setBackgroundColor(Color.CYAN)
                        val textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
                        textView.setTextColor(Color.BLUE)
                        snackBar.show()
//                        val snack = Snackbar.make(LinearLayout, "Invalid Credentials", Snackbar.LENGTH_LONG)
//                        snack.setAction("ok", View.OnClickListener {
//                            snack.dismiss()
//                        })
//                        snack.show()
                    }
                }

            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                            this@LoginActivity,
                            "Login error", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun saveData() {
        if (etUserName.text.isEmpty()) {
            etUserName.error = "Please enter Email"
            return
        }

        if (etPassword.text.isEmpty()) {
            etPassword.error = "Please enter Password"
            return
        }


    }

    fun Activity.saveSharedPref(email: String, password: String) {
        val sharedPref = getSharedPreferences("MyPref", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("email", email)
        editor.putString("password", password)
        editor.apply()
    }
}