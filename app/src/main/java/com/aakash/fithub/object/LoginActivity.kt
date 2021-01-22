package com.aakash.fithub.`object`

import android.content.Intent
import android.opengl.ETC1.isValid
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.aakash.fithub.MainActivity
import com.aakash.fithub.R

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var etUserName: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    private lateinit var signup: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        etUserName=findViewById(R.id.etUserName)
        etPassword=findViewById(R.id.etPassword)
        btnLogin=findViewById(R.id.btnLogin)
        signup=findViewById(R.id.signup)


        btnLogin.setOnClickListener(this)
        signup.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when(v?.id){

            R.id.signup -> {
                val intent= Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }

            R.id.btnLogin -> {
                if (isValid()) {
                    if (userValidator()) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Invalid username/password", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }

    private fun isValid():Boolean{
        when{
            etUserName.text.isEmpty()==true->{
                etUserName.error="Please enter username"
                return false
            }

            etPassword.text.isEmpty()==true->{
                etPassword.error="please enter password"
                return false
            }
        }
        return true
    }

    private fun userValidator(): Boolean {

//        val userName=etUserName.text.toString()
//        val password=etPassword.text.toString()
//        if(userName=="admin" && password=="admin"){
//            return true
//        }
        return false

    }
}